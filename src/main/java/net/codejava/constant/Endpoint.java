package net.codejava.constant;

public class Endpoint {
    public static final class V1 {
        public static final String PREFIX = "/api/v1";

        public static final class Admin {
            public static final String BASE = PREFIX + "/admin";
        }

        public static final class Auth {
            public static final String BASE = PREFIX + "/auth";
            public static final String LOGIN = BASE + "/login";
            public static final String REGISTER = BASE + "/register";
            public static final String CHANGE_PASSWORD = BASE + "/change-password";
            public static final String FORGOT_PASSWORD = BASE + "/forgot-password";
        }

        public static final class User {
            public static final String BASE = PREFIX + "/users";
            public static final String PROFILE = BASE + "/profile";
            public static final String GET_WALLET = BASE + "/wallet";
        }

        public static final class Car {
            public static final String BASE = PREFIX + "/cars";
            public static final String GET_LIST_FOR_OWNER = BASE + "/own";
            public static final String DETAILS = BASE + "/{id}";
            public static final String DETAILS_FOR_OWNER = BASE + "/own/{id}";
            public static final String STATUS = BASE + "/{id}/status";
            public static final String UPDATE_RATING = BASE + "/rating";
            public static final String LIST_CAR_BOOKINGS = BASE + "/own/{id}/bookings";
        }

        public static final class Booking {
            public static final String BASE = PREFIX + "/bookings";
            public static final String LIST_FOR_USER = BASE + "/own";
            public static final String DETAILS = BASE + "/{id}";
            public static final String CONFIRM_DEPOSIT = BASE + "/{id}/confirm-deposit";
            public static final String CONFIRM_PICK_UP = BASE + "/{id}/confirm-pickup";
            public static final String CONFIRM_PAYMENT = BASE + "/{id}/confirm-payment";
            public static final String CANCELLED_BOOKING = BASE + "/{id}/cancel";
            public static final String RETURN_CAR = BASE + "/{id}/return-car";
            public static final String FEEDBACK = BASE + "/{id}/feedback";
        }

        public static final class Transaction {
            public static final String BASE = PREFIX + "/transactions";
        }

        public static final class Feedback {
            public static final String BASE = PREFIX + "/feedbacks";
            public static final String LIST_FOR_OWNER = BASE + "/own";
            public static final String GET_RATING = BASE + "/rating";
        }

        public static final class Test {
            public static final String BASE = PREFIX + "/test";
            public static final String TEST01 = BASE + "/test01";
            public static final String TEST02 = BASE + "/test02";
        }
    }
}
