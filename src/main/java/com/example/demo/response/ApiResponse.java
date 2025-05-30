package com.example.demo.response;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 建立 Server 與 Client 在傳遞資料上的統一結構與標準(含錯誤)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
	private boolean success;
	private String message; // 訊息 例如: 查詢成功, 新增成功, 計算成功...
	private T data;         // payload 實際資料
	private String code;	//新增錯誤碼欄位，通常是字串或整數
	private Instant timestamp; // 新增時間戳，ISO 8601 格式字串或長整數時間戳
	
	// 成功回應(帶資料)
	public static <T> ApiResponse<T> success(String code, String message, T data) {
		ApiResponse<T> response = new ApiResponse<>();
		response.setSuccess(true);
		response.setMessage(message);
		response.setData(data);
		response.setCode(code);
		response.setTimestamp(Instant.now());
		return response;
	}
	// 成功回應(帶訊息)
	public static <T> ApiResponse<T> success(String message) {
		ApiResponse<T> response = new ApiResponse<>();
		response.setSuccess(true);
		response.setMessage(message);
		response.setData(null);
		response.setCode(null);
		response.setTimestamp(Instant.now());
		return response;
	}
	
	// 失敗回應(帶錯誤訊息與資料)
	public static <T> ApiResponse<T> error(String code, String message, T data)  {
		ApiResponse<T> response = new ApiResponse<>();
		response.setSuccess(false);
		response.setMessage(message);
		response.setData(data);
		response.setCode(code);
		response.setTimestamp(Instant.now());
		return response;
	}
	
	public static <T> ApiResponse<T> error(String message, T data) {
	    ApiResponse<T> response = new ApiResponse<>();
	    response.setSuccess(false);
	    response.setCode(null);
	    response.setMessage(message);
	    response.setData(data);
	    response.setTimestamp(Instant.now());
	    return response;
	}

	// 失敗回應(帶錯誤訊息)
	public static <T> ApiResponse<T> error(String message) {
		ApiResponse<T> response = new ApiResponse<>();
		response.setSuccess(false);
		response.setMessage(message);
		response.setData(null);
		response.setCode(null);
		response.setTimestamp(Instant.now());
		return response;
	}
}