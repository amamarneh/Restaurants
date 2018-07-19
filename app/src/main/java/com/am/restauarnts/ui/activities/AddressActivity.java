package com.am.restauarnts.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.repo.RepoFactory;
import com.am.restauarnts.ui.base.BaseActivity;
import com.am.restauarnts.ui.buychat.ChatActivity;
import com.am.restauarnts.ui.models.Cart;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.txtAddressName)
    EditText txtAddressName;
    @BindView(R.id.txtBuilding)
    EditText txtBuilding;
    @BindView(R.id.txtFloor)
    EditText txtFloor;
    @BindView(R.id.tvMobile)
    TextView tvMobile;
    @OnClick(R.id.btnSendOrder)
    void sendOrder(){
        if(validate()){
            progressBar.setVisibility(View.VISIBLE);
            String address = txtAddressName.getText().toString().trim() + ",Building: " + txtBuilding.getText().toString().trim() + ",Floor: " + txtFloor.getText().toString().trim();
            RepoFactory.getRepoInstance()
                    .sendOrder(Cart.getCart(this),address)
                    .observe(this,integerLiveResponse -> {
                        if (integerLiveResponse==null)return;
                        progressBar.setVisibility(View.GONE);
                        if (integerLiveResponse.isSuccessful()){
                            Log.d("tag","success, orderId="+integerLiveResponse.data);
                            Intent intent = ChatActivity.getChatIntent(this,integerLiveResponse.data);
                            startActivity(intent);

                        }else{
                            Log.d("tag","failed");
                        }
                    });

        }
    }
    boolean validate(){
        EditText source = txtAddressName;
        if(TextUtils.isEmpty(source.getText())){
            source.setError("Enter address name");
            source.requestFocus();
            return false;
        }
        source = txtBuilding;
        if(TextUtils.isEmpty(source.getText())){
            source.setError("Field required");
            source.requestFocus();
            return false;
        }
        source = txtFloor;
        if(TextUtils.isEmpty(source.getText())){
            source.setError("Field required");
            source.requestFocus();
            return false;
        }

        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        setupBackArrow();
        setTitle("Address");
        progressBar.setVisibility(View.GONE);
        //
        tvMobile.setText(RepoFactory.getUserRepo().getCurrentUser().getPhone());
    }
}
