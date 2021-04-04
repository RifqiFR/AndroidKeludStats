package com.keludstats.modul.login;

import com.keludstats.base.util.UtilProvider;
import com.keludstats.shared.apiresponse.APIResponseCollection;
import com.keludstats.shared.callback.RequestCallback;
import com.keludstats.shared.callback.RetrofitCallback;
import com.keludstats.shared.model.Token;
import com.keludstats.shared.model.User;
import com.keludstats.shared.retrofit.ServiceGenerator;
import com.keludstats.shared.util.TokenUtil;
import com.keludstats.shared.util.UserUtil;

import retrofit2.Call;

public class LoginInteractor implements LoginContract.Interactor {
    private static final String TAG = LoginInteractor.class.getSimpleName();
    private final LoginService service;

    public LoginInteractor() {
        service = ServiceGenerator.createService(LoginService.class);
    }

    @Override
    public void requestLogin(String email, String pass, RequestCallback<Token> callback) {
        Call<Token> call = service.login(new User(email, pass));
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestLogin"));
    }

    @Override
    public void addTokenToAllNextRequest(Token token) {
        ServiceGenerator.createService(LoginService.class, token.getToken());
    }

    @Override
    public void requestSaveToken(Token token) {
        TokenUtil tokenUtil = (TokenUtil) UtilProvider.getUtil(TokenUtil.class);
        tokenUtil.initialize(token);
    }

    @Override
    public void requestSaveUser(User user) {
        UserUtil userUtil = (UserUtil) UtilProvider.getUtil(UserUtil.class);
        userUtil.initialize(user);
    }
}
