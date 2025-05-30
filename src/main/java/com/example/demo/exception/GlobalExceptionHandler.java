//@ControllerAdvice 和 @ExceptionHandler 提供了一種統一處理異常的方法
//Exception.class指的是捕獲所有類型為 Exception 的異常
//Exception.class 用來告訴 Spring 當出現任何 Exception 類型的異常時，都會執行 handleException() 方法
//e.getClass().getSimpleName() -> e 是異常物件本身 -> e.getClass() 會返回這個異常物件的類型 -> getSimpleName() 會返回這個類型的簡單名稱

/*
 * NoResourceFoundException → 404 Not Found
 * MethodArgumentNotValidException → 400 Bad Request（DTO 驗證錯誤）
 * MethodArgumentTypeMismatchException → 400 Bad Request（參數類型錯誤）
 * 其他未捕捉異常 → 500 Internal Server Error
 * */
package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.example.demo.response.ApiResponse;

//@ControllerAdvice 和 @ResponseBody 的組合，不必在每個 handler 方法都寫回傳物件，直接回 JSON
//(讓這個類別成為全域 REST API 的異常攔截器)
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 此為自訂異常類別
     * 處理找不到資源異常
     * 回傳 404 Not Found
     */
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(NoResourceFoundException e){
		ApiResponse<Object> response = ApiResponse.error(e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	/**
     * 處理 DTO 驗證失敗異常（@Valid 驗證失敗）
     * 回傳 400 Bad Request，並附上欄位錯誤訊息
     * fieldName 是出錯的欄位名(Key)  
     * message 是該欄位的錯誤描述(Value)
     */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(error -> {
			//從 Spring 的驗證錯誤物件中取得「欄位名稱」
			//e.getBindingResult().getAllErrors() 會回傳一個 List<ObjectError>
			//ObjectError 是驗證錯誤的父類別，FieldError 是 ObjectError 的子類別
			//error 原本是 ObjectError，強制轉型為 FieldError，呼叫 getField() 方法
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		ApiResponse<Map<String, String>> response = ApiResponse.error("驗證失敗", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		
	}
	
	/**
     * 處理參數類型錯誤異常（例如 URL 參數型態錯誤）
     * 回傳 400 Bad Request
     */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ApiResponse<Object>> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e){
		//getName() 方法會回傳「出錯的參數名稱」
		String message = "參數類型錯誤:" + e.getName();
		ApiResponse<Object> response = ApiResponse.error(message);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	
	/**
     * 處理所有其他未捕捉的異常
     * 回傳 500 Internal Server Error
     */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>>handleAllExceptions(Exception e){
		ApiResponse<Object> response = ApiResponse.error("系統錯誤，請稍後再試");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		
	}
	
	

}