package com.wits.grofast_user.Api.interfaces;

import com.wits.grofast_user.Api.responseClasses.OrderPlaceResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderInterface {
    @POST("place-user-order")
    Call<OrderPlaceResponse> placeOrder(@Query("total_amount") Integer totalAmount, @Query("coupon") String couponCode, @Query("discount") float discount, @Query("delivery_charges") int deleveryCharges, @Query("cgst") float cgst, @Query("sgst") float sgst, @Query("tip") int tip, @Query("additional_note") String aditionalNote, @Query("address_id") int addressId, @Query("receiver_name") String receiverName, @Query("receiver_phone_no") Integer receiverPhone, @Query("payment_metod") int paymentMethod);
}
