package com.java.todolist.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Task {

    private String message;
    private Date addedDate;

    public static class Builder {
        private String message;
        private Date addedDate;

        public Builder(String message) {
            this.message = message;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setAddedDate(Date date) {
            this.addedDate = date;
            return this;
        }

        public Task build() {
            return new Task(this.message, this.addedDate);
        }
    }
}
