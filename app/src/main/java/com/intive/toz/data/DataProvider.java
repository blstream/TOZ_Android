package com.intive.toz.data;

import com.intive.toz.account.model.ResponseMessage;
import com.intive.toz.account.model.UserPassword;
import com.intive.toz.login.model.Login;
import com.intive.toz.login.model.User;
import com.intive.toz.news.model.News;
import com.intive.toz.petDetails.model.Comment;
import com.intive.toz.petslist.model.Pet;
import com.intive.toz.reset_password.model.Email;
import com.intive.toz.schedule.model.Reservation;
import com.intive.toz.schedule.model.Reserve;
import com.intive.toz.schedule.model.Schedule;
import com.intive.toz.volunteerForm.model.BecomeVolunteerInfo;
import com.intive.toz.volunteerForm.model.Proposal;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;

/**
 * The interface Data provider.
 */
public interface DataProvider {
    /**
     * Fetch only released news.
     *
     * @param listener the listener
     */
    void fetchReleasedNews(ResponseCallback<List<News>> listener);

    /**
     * Fetch all news.
     *
     * @param listener the listener
     */
    void fetchAllNews(ResponseCallback<List<News>> listener);

    /**
     * Fetch pets.
     *
     * @param listener the listener
     */
    void fetchPets(ResponseCallback<List<Pet>> listener);

    /**
     * Fetch just one object of News.
     *
     * @param listener the listener
     * @param id       id
     */
    void fetchDetailNews(ResponseCallback<News> listener, String id);

    /**
     * Fetch pet.
     *
     * @param petID    pet id
     * @param listener listener
     */
    void fetchPetDetails(String petID, ResponseCallback<Pet> listener);

    /**
     * Checking login account on server and response with Jwt.
     *
     * @param listener object response from server.
     * @param loginObj object contain email and password.
     */
    void fetchResponseLogin(ResponseLoginCallback<User> listener, Login loginObj);

    /**
     * Fetch schedule.
     *
     * @param listener the listener
     * @param from     the from
     * @param to       the to
     */
    void fetchSchedule(ResponseCallback<Schedule> listener, String from, String to);

    /**
     * Reserve.
     *
     * @param listener the listener
     * @param reserve  the reserve
     */
    void reserve(ResponseCallback<Reservation> listener,
                 Reserve reserve);

    /**
     * Remove reservation.
     *
     * @param listener the listener
     * @param id       the id
     */
    void removeReservation(ResponseCallback<ResponseBody> listener, String id);

    /**
     * Making request to server to change password.
     *
     * @param listener     listener
     * @param userPassword objects containing old and new password
     */
    void requestPasswordChange(ResponseChangePasswordCallback<ResponseMessage> listener, UserPassword userPassword);

    /**
     * Add pet.
     *
     * @param listener the listener
     * @param pet      the pet
     */
    void addPet(ResponseCallback<Pet> listener, Pet pet);

    /**
     * Upload image.
     *
     * @param listener the listener
     * @param id       the id
     * @param file     the file
     */
    void uploadImage(ResponseCallback<ResponseBody> listener, String id, MultipartBody.Part file);

    /**
     * Reset password.
     *
     * @param listener the listener
     * @param email    the email
     */
    void resetPassword(ResponseCallback<ResponseBody> listener, Email email);

    /**
     * Proposal.
     *
     * @param listener the listener
     * @param proposal the proposal
     */
    void proposal(ResponseCallback<Integer> listener, Proposal proposal);

    /**
     * Become volunteer.
     *
     * @param listener the listener
     */
    void becomeVolunteer(ResponseCallback<BecomeVolunteerInfo> listener);

    /**
     * Pet comments.
     *
     * @param listener the listener
     * @param id       the id
     * @param state    the state
     */
    void petComments(ResponseCallback<List<Comment>> listener, String id, String state);

    /**
     * Add comment.
     *
     * @param listener the listener
     * @param comment  the comment
     */
    void addComment(ResponseCallback<Comment> listener, Comment comment);

    /**
     * All comments.
     *
     * @param listener the listener
     */
    void allComments(ResponseCallback<List<Comment>> listener);

    /**
     * The interface On data received listener.
     *
     * @param <T> the type parameter
     */
    interface ResponseCallback<T> {
        /**
         * On success.
         *
         * @param response the response
         */
        void onSuccess(T response);

        /**
         * On error.
         *
         * @param e the e
         */
        void onError(Throwable e);
    }

    /**
     * The interfaca for listener to login.
     *
     * @param <T> the type parameter
     */
    interface ResponseLoginCallback<T> {
        /**
         * On success.
         *
         * @param response the response
         */
        void onSuccess(T response);

        /**
         * On error.
         *
         * @param e the e
         */
        void onError(Throwable e);

        /**
         * successfull attempt to login but response with specific error code.
         *
         * @param codeMessage HTTP Status Code
         */
        void onErrorCode(int codeMessage);

        /**
         * wrong password.
         */
        void onErrorPassword();

        /**
         * login not exist in database.
         */
        void onErrorLogin();
    }

    /**
     * Interface for changing password listener.
     *
     * @param <T> the type parameter
     */
    interface ResponseChangePasswordCallback<T> {
        /**
         * On success.
         *
         * @param response the response
         */
        void onSuccess(T response);

        /**
         * On error.
         *
         * @param e the e
         */
        void onError(Throwable e);

        /**
         * on either wrong old password or new and old password being equal.
         */
        void onWrongPassword();

        /**
         * Response with error code, e.g. 404 not found/
         *
         * @param errorCode error code
         */
        void onErrorCode(int errorCode);
    }
}

