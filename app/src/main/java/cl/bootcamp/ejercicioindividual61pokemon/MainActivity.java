package cl.bootcamp.ejercicioindividual61pokemon;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.Resources;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup pokemonGroup;
    private Button validateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokemonGroup = findViewById(R.id.pokemon_group);
        validateButton = findViewById(R.id.validate_button);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectId = pokemonGroup.getCheckedRadioButtonId();
                if (selectId == -1) {
                    Toast.makeText(MainActivity.this, "Selecciona un Pokemon", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedPokemon = findViewById(selectId);
                    String pokemonName = selectedPokemon.getText().toString();
                    showPokemonDialog(pokemonName);
                }
            }
        });
    }

    private void showPokemonDialog(String pokemonName) {
        //creando dialogo
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_pokemon, null);
        builder.setView(dialogView);

        //configurando elementos del dialogo
        androidx.appcompat.app.AlertDialog dialog = builder.create();
        dialogView.findViewById(R.id.dialog_close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView title = dialogView.findViewById(R.id.dialog_title);
        TextView pokemonSelected = dialogView.findViewById(R.id.dialog_pokemon_name);

        //asignar valores dinamicos
        title.setText("Pokémon Seleccionado");
        pokemonSelected.setText(pokemonName);

        //colorear texto segun pokemon
        if (pokemonName.equalsIgnoreCase("Charmander")) {
            pokemonSelected.setTextColor(getColorCompat(R.color.charmander));
        } else if (pokemonName.equalsIgnoreCase("Bulbasaur")) {
            pokemonSelected.setTextColor(getColorCompat(R.color.bulbasaur));
        } else if (pokemonName.equalsIgnoreCase("Squirtle")) {
            pokemonSelected.setTextColor(getColorCompat(R.color.squirtle));
        }

        //mostrar dialogo
        dialog.show();
    }

    private int getColorCompat(int colorResId) {
        return getResources().getColor(colorResId, getTheme());
    }
}