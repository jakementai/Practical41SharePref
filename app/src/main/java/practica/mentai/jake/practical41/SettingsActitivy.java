package practica.mentai.jake.practical41;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActitivy extends AppCompatActivity {

    private static final String PREFF_FILE = "practica.mentai.jake.practical41.PREFF_FILE";
    private EditText editText;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private ImageView imageViewProfile;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_actitivy);

        editText = findViewById(R.id.editText);
        radioGroupGender = findViewById(R.id.radio_group_gender);
        radioButtonMale = findViewById(R.id.radio_butt_male);
        radioButtonFemale = findViewById(R.id.radio_butt_female);
        imageViewProfile = findViewById(R.id.imageView2);

        sharedPreferences = getSharedPreferences(PREFF_FILE, MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String name;
        int gender = -1; // -1 = default; 1 = male; 0 = female
        name = sharedPreferences.getString(
                getString(R.string.user_name),
                getString(R.string.no_name));

        gender = sharedPreferences.getInt(
                getString(R.string.user_gender),
                -1);

        editText.setText(name);
        if(gender == 1){
            radioButtonMale.setChecked(true);
            imageViewProfile.setImageResource(R.drawable.male);
        }else if(gender == 0){
            radioButtonFemale.setChecked(true);
            imageViewProfile.setImageResource(R.drawable.female);

        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        //Create an editor to store the preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String name =  editText.getText().toString();
        int gender = -1;

        if(radioGroupGender.getCheckedRadioButtonId() == R.id.radio_butt_male){
            gender = 1;
        }else if(radioGroupGender.getCheckedRadioButtonId() == radioButtonFemale.getId()){
            gender = 0;
        }

        editor.putString(getString(R.string.user_name), name);
        editor.putInt(getString(R.string.user_gender), gender);

        editor.commit();
    }
}
