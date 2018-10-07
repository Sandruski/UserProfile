package info.sandraag.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UserProfileActivity extends AppCompatActivity {

    private ImageView backgroundView;
    private ImageView profileView;
    private TextView nameView;
    private TextView usernameView;
    private TextView descriptionView;
    private TextView numFollowingView;
    private TextView numFollowersView;

    private UserProfile userProfile;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        gson = new Gson();

        backgroundView = findViewById(R.id.backgroundView);
        profileView = findViewById(R.id.profileView);
        nameView = findViewById(R.id.nameView);
        usernameView = findViewById(R.id.usernameView);
        descriptionView = findViewById(R.id.descriptionView);
        numFollowingView = findViewById(R.id.numFollowingView);
        numFollowersView = findViewById(R.id.numFollowersView);

        try {
            InputStream stream = getAssets().open("Sandra.json");
            InputStreamReader reader = new InputStreamReader(stream);
            userProfile = gson.fromJson(reader, UserProfile.class);
        }
        catch (IOException e)
        {
            Toast.makeText(this, "json could not be read", Toast.LENGTH_SHORT).show();
        }

        Glide.with(this)
                .load("file:///android_asset/SandraProfile.JPG")
                .apply(RequestOptions.circleCropTransform())
                .into(profileView);

        Glide.with(this)
                .load("file:///android_asset/SandraBackground.JPG")
                .into(backgroundView);

        updateUserProfile();
    }

    private void updateUserProfile() {

        nameView.setText(userProfile.getName() + " " + userProfile.getLastname());
        usernameView.setText("@" + userProfile.getHandle());
        numFollowingView.setText(userProfile.getFollowing());
        numFollowersView.setText(userProfile.getFollowers());
        descriptionView.setText(userProfile.getAbout());
    }
}
