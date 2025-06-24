function incrementQuantity(productId) {
    const input = document.getElementById('qty-' + productId);
    if (input) {
        const currentValue = parseInt(input.value);
        input.value = isNaN(currentValue) ? 1 : currentValue + 1;
        input.dispatchEvent(new Event('change'));
    }
}

function updateTotalAmount() {
    let total = 0;
    document.querySelectorAll('input[type="number"]').forEach(input => {
        const priceInput = input.closest('tr').querySelector('input[type="hidden"][name*="unitPrice"]');
        const price = parseFloat(priceInput.value);
        const quantity = parseInt(input.value, 10) || 0;
        total += price * quantity;
    });
    document.getElementById('totalAmount').textContent = total.toFixed(2);
}

document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('input[type="number"]').forEach(input => {
        input.addEventListener('input', updateTotalAmount);
        input.addEventListener('change', updateTotalAmount);
    });

    document.getElementById('customerPhone').addEventListener('input', function () {
        const keyword = this.value;
        if (keyword.length < 2) return;

        fetch(`/api/customers/autocomplete?keyword=${encodeURIComponent(keyword)}`)
            .then(res => res.json())
            .then(customers => {
                const datalist = document.getElementById('customerOptions');
                datalist.innerHTML = '';
                customers.forEach(c => {
                    const option = document.createElement('option');
                    option.value = c.phone;
                    option.textContent = `${c.name} (${c.phone})`;
                    datalist.appendChild(option);
                });
            });
    });

	document.getElementById('customerPhone').addEventListener('blur', function () {
	    const phone = this.value;
	    if (!phone) return;

	    fetch(`/api/customers/find-by-phone?phone=${encodeURIComponent(phone)}`)
	        .then(response => {
	            if (!response.ok) {
	                // 若是 404，直接觸發新增流程
	                if (confirm("查無此電話的顧客，是否新增？")) {
	                    openModal(phone);
	                }
	                throw new Error("Not Found");
	            }
	            return response.json();
	        })
	        .then(data => {
	            if (data && data.name) {
	                document.getElementById('customerName').value = data.name;
	            }
	        })
	        .catch(err => {
	            console.error('查詢錯誤:', err);
	            // 不要再彈出 confirm，前面已經處理過
	        });
	});


    document.getElementById('newCustomerForm').addEventListener('submit', function (e) {
        e.preventDefault();
		console.log('👀 送出新增顧客表單'); // 👈 應該要看到這行
        const customer = {
            name: document.getElementById('newCustomerName').value,
            phone: document.getElementById('newCustomerPhone').value,
            remark: document.getElementById('newCustomerRemark').value
        };

        fetch('/api/customers/create', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(customer)
        })
            .then(res => {
                if (!res.ok) throw new Error("儲存失敗");
                return res.json();
            })
            .then(data => {
                document.getElementById('customerName').value = data.name;
                document.getElementById('customerPhone').value = data.phone;
                closeModal();
                alert("顧客新增成功！");
            })
            .catch(error => {
                alert("新增失敗：" + error.message);
            });
    });
});


function openModal(phone) {
    document.getElementById('newCustomerForm').reset();
    document.getElementById('newCustomerPhone').value = phone || '';
    document.getElementById('customerModal').style.display = 'block';
}

function closeModal() {
    document.getElementById('customerModal').style.display = 'none';
}

console.log("✅ order.js 載入成功");
