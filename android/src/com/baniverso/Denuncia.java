package com.baniverso;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Denuncia extends Activity {
	private String tipoDenuncia;
	private TextView meioTransporte;
	private TextView linha;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button denunciaComercio = (Button) findViewById(R.id.denunciaComercio);
		denunciaComercio.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				gravaTipoDenuncia("Comércio");
			}
		});

		final Button denunciaDelito = (Button) findViewById(R.id.denunciaDelito);
		denunciaDelito.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				gravaTipoDenuncia("Delito");
			}
		});

		final Button denunciaVandalismo = (Button) findViewById(R.id.denunciaVandalismo);
		denunciaVandalismo.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				gravaTipoDenuncia("Vandalismo");
			}
		});

	}
	
	private void showLinha() {
		String meio = meioTransporte.getText().toString();
		//final LinearLayout linhaArea = (LinearLayout) findViewById(R.id.linha0);
		//linhaArea.setVisibility(View.VISIBLE);

		final Button linha = (Button) findViewById(R.id.linha1);
		linha.setVisibility(View.VISIBLE);
		linha.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				chooseLinha();
			}
		});
		
	}
	
	private void chooseLinha() {
		final CharSequence[] items = getResources().getStringArray(R.array.linhas_metro);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Linha do " + meioTransporte.getText());
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				linha = (TextView) findViewById(R.id.linha2);
				linha.setText(items[item]);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	private void showMeioTransporte() {
		final Button meioTransporte = (Button) findViewById(R.id.meioTransporte1);
		meioTransporte.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				chooseMeioTransporte();
			}
		});
	}
	
	private void chooseMeioTransporte() {
		final CharSequence[] items = { "Trem", "Metrô" };
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Meio de transporte");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				meioTransporte = (TextView) findViewById(R.id.meioTransporte2);
				meioTransporte.setText(items[item]);
				showLinha();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	private void gravaTipoDenuncia(String tipo) {
		this.tipoDenuncia = tipo;
		setContentView(R.layout.local);
		showMeioTransporte();
	}
}