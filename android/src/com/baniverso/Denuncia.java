package com.baniverso;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Denuncia extends Activity {
	private static final Map<String,Integer> LINHAS = new HashMap<String,Integer>();
	private String tipoDenuncia;
	private TextView meioTransporte;
	private TextView linha;
	private TextView estacao;
	
	static {
		LINHAS.put("Azul", R.array.linha1);
		LINHAS.put("Verde", R.array.linha2);
		LINHAS.put("Vermelha", R.array.linha3);
		LINHAS.put("Amarela", R.array.linha4);
		LINHAS.put("Lilás", R.array.linha5);
		
		LINHAS.put("Rubi", R.array.linha7);
		LINHAS.put("Diamante", R.array.linha8);
		LINHAS.put("Esmeralda", R.array.linha9);
		LINHAS.put("Turquesa", R.array.linha10);
		LINHAS.put("Coral", R.array.linha11);
		LINHAS.put("Safira", R.array.linha12);
	}

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

	private void gravaTipoDenuncia(String tipo) {
		this.tipoDenuncia = tipo;
		setContentView(R.layout.local);
		// inicializa campos de informação do local
		meioTransporte = (TextView) findViewById(R.id.meioTransporte2);
		linha = (TextView) findViewById(R.id.linha2);
		estacao = (TextView) findViewById(R.id.estacao2);

		showMeioTransporte();
	}

	// método auxiliar para resetar as listas já escolhidas a partir de certo
	// ponto
	private void limpaLocal(int passo) {
		switch (passo) {
		case 1:
			linha.setText("");
		case 2:
			estacao.setText("");
		case 3:
		}

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
				meioTransporte.setText(items[item]);
				showLinha();
				limpaLocal(1);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	private void showLinha() {
		final Button linha = (Button) findViewById(R.id.linha1);
		linha.setVisibility(View.VISIBLE);
		linha.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				chooseLinha();
			}
		});
	}

	private void chooseLinha() {
		String meio = meioTransporte.getText().toString();
		final CharSequence[] items = meio.equalsIgnoreCase("Trem") ? getResources()
				.getStringArray(R.array.linhas_trem) : getResources()
				.getStringArray(R.array.linhas_metro);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Linha do " + meioTransporte.getText());
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				linha.setText(items[item]);
				showEstacao();
				limpaLocal(2);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	private void showEstacao() {
		final Button estacao = (Button) findViewById(R.id.estacao1);
		estacao.setVisibility(View.VISIBLE);
		estacao.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				chooseEstacao();
			}
		});
	}

	private void chooseEstacao() {
		String linha = this.linha.getText().toString();
		final CharSequence[] items = getResources().getStringArray(LINHAS.get(linha));
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Linha do " + meioTransporte.getText());
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				estacao.setText(items[item]);
				//showEstacao();
				//limpaLocal(2);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
}