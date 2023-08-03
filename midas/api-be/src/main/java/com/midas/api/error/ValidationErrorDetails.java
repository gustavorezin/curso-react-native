package com.midas.api.error;

public class ValidationErrorDetails extends ErrorDetails {
	private String field;
	private String fieldMessage;

	public static final class Builder {
		private String title;
		private int status;
		private String detail;
		private long timestamp;
		private String developerMessage;
		private String field;
		private String fieldMessage;

		private Builder() {
		}

		public static ValidationErrorDetails.Builder newBuilder() {
			return new ValidationErrorDetails.Builder();
		}

		public ValidationErrorDetails.Builder title(String title) {
			this.title = title;
			return this;
		}

		public ValidationErrorDetails.Builder status(int status) {
			this.status = status;
			return this;
		}

		public ValidationErrorDetails.Builder detail(String detail) {
			this.detail = detail;
			return this;
		}

		public ValidationErrorDetails.Builder timestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public ValidationErrorDetails.Builder developerMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}

		public ValidationErrorDetails.Builder field(String field) {
			this.field = field;
			return this;
		}

		public ValidationErrorDetails.Builder fieldMessage(String fieldMessage) {
			this.fieldMessage = fieldMessage;
			return this;
		}

		public ValidationErrorDetails build() {
			ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
			validationErrorDetails.setTimestamp(timestamp);
			validationErrorDetails.setDeveloperMessage(developerMessage);
			validationErrorDetails.setDetail(detail);
			validationErrorDetails.setTitle(title);
			validationErrorDetails.setStatus(status);
			validationErrorDetails.field = field;
			validationErrorDetails.fieldMessage = fieldMessage;
			return validationErrorDetails;
		}
	}

	public String getField() {
		return field;
	}

	public String getFieldMessage() {
		return fieldMessage;
	}
}
