package com.am.restauarnts;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;

        import com.am.restauarnts.ui.activities.HomeActivity;
        import com.am.restauarnts.ui.models.Cart;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //a
        Cart.getCart(this).clear(this);
        startActivity(new Intent(this, HomeActivity.class));
    }
}
