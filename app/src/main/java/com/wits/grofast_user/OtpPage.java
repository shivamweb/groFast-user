package com.wits.grofast_user;

import static com.wits.grofast_user.CommonUtilities.handleApiError;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.wits.grofast_user.Api.RetrofitService;
import com.wits.grofast_user.Api.interfaces.UserInterface;
import com.wits.grofast_user.Api.responseClasses.LoginResponse;
import com.wits.grofast_user.MainHomePage.HomePage;
import com.wits.grofast_user.session.UserActivitySession;
import com.wits.grofast_user.session.UserDetailSession;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpPage extends AppCompatActivity {

    AppCompatButton Continue, resend;
    TextView phone, countDownTimer;
    String receivedPhone, receivedOtp, enteredOtp = "";
    EditText digit1, digit2, digit3, digit4;
    long COUNTDOWN_TIME_MILLIS = 30000;
    String TAG = "OtpPage";
    LinearLayout loadingOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_otp_page);

        loadingOverlay = findViewById(R.id.loading_overlay);

        UserActivitySession session = new UserActivitySession(getApplicationContext());
        UserDetailSession userDetailSession = new UserDetailSession(getApplicationContext());

        Intent intent = getIntent();
        if (intent != null) {
            receivedPhone = intent.getStringExtra("mobileNo");
            receivedOtp = intent.getStringExtra("mobileOtp");

            Log.e(TAG, "onCreate: receivedPhone " + receivedPhone);
            Log.e(TAG, "onCreate: receivedOtp " + receivedOtp);
        }

        digit1 = findViewById(R.id.otp_digit1);
        digit2 = findViewById(R.id.otp_digit2);
        digit3 = findViewById(R.id.otp_digit3);
        digit4 = findViewById(R.id.otp_digit4);
        setEditTextListeners();

        Continue = findViewById(R.id.Continue_otp_page);
        resend = findViewById(R.id.resend_otp_button);
        phone = findViewById(R.id.otp_phone_no);
        countDownTimer = findViewById(R.id.countdown_timer);
        phone.setText(receivedPhone);
        startCountdown();
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomePage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                enteredOtp = digit1.getText().toString().trim() + digit2.getText().toString().trim() + digit3.getText().toString().trim() + digit4.getText().toString().trim();
                Log.e(TAG, "onCreate: enteredOtp " + enteredOtp);
                Log.e(TAG, "onCreate: receivedOtp " + receivedOtp);

                if (isOtpValid()) {
                    loadingOverlay.setVisibility(View.VISIBLE);
                    if (enteredOtp.equals(receivedOtp)) {
                        session.setLoginStaus(true);
                        userDetailSession.setPhoneNo(receivedPhone);
                        startActivity(i);
                        loadingOverlay.setVisibility(View.GONE);
                    } else {
                        loadingOverlay.setVisibility(View.GONE);
                        showToastAndFocus(getString(R.string.toast_message_correct_otp), digit4);
                    }
                } else {
                    showToastAndFocus(getString(R.string.toast_message_enter_otp), digit1);
                }
            }
        });
        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countDownTimer.getText().toString().equals("00:00")) {
                    loadingOverlay.setVisibility(View.VISIBLE);
                    Call<LoginResponse> call = RetrofitService.getClient().create(UserInterface.class).login(receivedPhone);

                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                startCountdown();
                                LoginResponse loginResponse = response.body();
                                if (loginResponse != null) {
                                    receivedOtp = loginResponse.getOtp();
                                    Toast.makeText(getApplicationContext(), "" + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    loadingOverlay.setVisibility(View.GONE);
                                }
                            } else {
                                handleApiError(TAG, response, getApplicationContext());
                                loadingOverlay.setVisibility(View.GONE);
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            t.printStackTrace();
                            loadingOverlay.setVisibility(View.GONE);
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), getString(R.string.toast_message_resend_otp), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isOtpValid() {
        boolean valid = true;
        if (digit1.getText().toString().trim().isEmpty()) {
            valid = false;
        }

        if (digit2.getText().toString().trim().isEmpty()) {
            valid = false;
        }
        if (digit3.getText().toString().trim().isEmpty()) {
            valid = false;
        }

        if (digit4.getText().toString().trim().isEmpty()) {
            valid = false;
        }

        return valid;
    }

    private void setEditTextListeners() {
        digit1.requestFocus();
        digit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    digit2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        digit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    digit3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        digit3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    digit4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        digit2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    if (digit2.getText().toString().isEmpty()) {
                        digit1.requestFocus();
                    }
                }
                return false;
            }
        });

        digit3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    if (digit3.getText().toString().isEmpty()) {
                        digit2.requestFocus();
                    }
                }
                return false;
            }
        });

        digit4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                    if (digit4.getText().toString().isEmpty()) {
                        digit3.requestFocus();
                    }
                }
                return false;
            }
        });
    }

    private void startCountdown() {
        new CountDownTimer(COUNTDOWN_TIME_MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int) (millisUntilFinished / 1000) / 60;
                int seconds = (int) (millisUntilFinished / 1000) % 60;
                String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
                countDownTimer.setText(timeLeftFormatted);

                resend.setClickable(false);
                resend.setBackgroundDrawable(getDrawable(R.drawable.textview_design));
                resend.setTextColor(getColor(R.color.default_color));
            }

            @Override
            public void onFinish() {
                resend.setClickable(true);
                countDownTimer.setText("00:00");
                resend.setBackgroundDrawable(getDrawable(R.drawable.color_button));
                resend.setTextColor(getColor(R.color.button_text_color));
            }
        }.start();
    }

    private void showToastAndFocus(String message, EditText editText) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        editText.requestFocus();
        showKeyboard(editText);
    }

    private void showKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }
}