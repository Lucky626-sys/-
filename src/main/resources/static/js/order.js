function incrementQuantity(productId){
	// 	點商品圖片時，會呼叫 incrementQuantity(productId) 這個 JSc函數：
	//會根據商品的 id，找到對應的 <input> 數量欄位（例如 id="qty-4"）
    var input = document.getElementById('qty-' + productId);
    if(input){
		// 取得目前輸入框的值，並轉成整數
        var currentValue = parseInt(input.value);
        if(isNaN(currentValue)){
            currentValue = 0;
        }
		// 把目前的值（數量）讀出來，加 1，再寫回輸入框
        input.value = currentValue + 1;
    }
	console.log("order.js loaded");
}
