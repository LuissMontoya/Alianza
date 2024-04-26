package co.com.test.alianza.appClients.utils;

import co.com.test.alianza.appClients.DTO.ResponseDTO;

public class Utils {

	/**
	 * Método que permite mapear la respuesta de un servicio.
	 */
	public static ResponseDTO mapearRespuesta(final String message, final Integer statusCode,
			final Object objResponse) {
		ResponseDTO response = new ResponseDTO();
		response.setMessage(message);
		response.setStatusCode(statusCode);
		response.setObjectResponse(objResponse);

		return response;
	}
	
	/**
	 * Método que permite mapear la respuesta de un servicio.
	 */
	public static ResponseDTO mapearRespuesta(final String message, final Integer statusCode) {
		ResponseDTO response = new ResponseDTO();
		response.setMessage(message);
		response.setStatusCode(statusCode);
		response.setObjectResponse(null);

		return response;
	}
}
