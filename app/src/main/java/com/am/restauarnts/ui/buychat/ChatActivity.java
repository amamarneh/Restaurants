package com.am.restauarnts.ui.buychat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.am.restauarnts.R;
import com.am.restauarnts.data.network.FirebaseEndPoint;
import com.am.restauarnts.repo.RepoFactory;
import com.am.restauarnts.ui.base.BaseActivity;
import com.am.restauarnts.ui.buychat.model.BuyMessage;
import com.am.restauarnts.ui.buychat.model.ChatModel;
import com.am.restauarnts.ui.buychat.model.MessageText;
import com.am.restauarnts.ui.models.OrderDetailsModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerViewOrder)
    RecyclerView recyclerViewOrder;
    @BindView(R.id.btnSend)
    Button btnSend;
    @BindView(R.id.txtMessage)
    EditText txtMessage;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvDate)
    TextView tvDate;

    @OnClick(R.id.btnSend)
    void send(){
        String text = txtMessage.getText().toString().trim();
        MessageText msg= new MessageText();
        msg.setSender_id(RepoFactory.getUserRepo().getCurrentUser().getId());
        msg.setSender_name(RepoFactory.getUserRepo().getCurrentUser().getName());
        msg.setText(text);
        msg.setType(BuyMessage.TYPE_TEXT);
        RepoFactory.getRepoInstance()
                .sendMessage(mOrderId+"",msg);
        txtMessage.setText("");
    }

    private FirestoreRecyclerAdapter mAdapter;
    private int mOrderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        setupBackArrow();
        setTitle("Order");

        mOrderId = getIntent().getIntExtra("orderId",-1);

        Query query = FirebaseEndPoint.getChatMessages(mOrderId+"").orderBy("created");
        FirestoreRecyclerOptions<BuyMessage> options
                = new FirestoreRecyclerOptions.Builder<BuyMessage>()
                .setQuery(query,new MySnapshotParser())
                .build();
        mAdapter = new ChatAdapter(options);
        final LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int friendlyMessageCount = mAdapter.getItemCount();
                int lastVisiblePosition =
                        mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (friendlyMessageCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    recyclerView.scrollToPosition(positionStart);
                }
            }
        });
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(mAdapter);

        recyclerViewOrder.setLayoutManager(new LinearLayoutManager(this));


        RepoFactory.getRepoInstance()
                .getOrderDetails(mOrderId)
                .observe(this,response->{
                    if (response == null) return;
                    if(response.isSuccessful()){
                        updateUI(response.data);
                    }
                });
    }

    private void updateUI(OrderDetailsModel orderDetailsModel) {
        if(orderDetailsModel == null) return;
        OrdersBriefAdapter ordersBriefAdapter = new OrdersBriefAdapter(orderDetailsModel.getItems());
        recyclerViewOrder.setAdapter(ordersBriefAdapter);
        tvDate.setText(orderDetailsModel.getCreated().toString());
        tvName.setText(orderDetailsModel.getRestaurant().getName());
    }

    public static Intent getChatIntent(Context context, int orderId){
        Intent i = new Intent(context,ChatActivity.class);
        i.putExtra("orderId",orderId);
        return i;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }
}
