package com.baniverso;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Denuncia extends Activity {
	private static final Map<String,Integer> LINHAS = new HashMap<String,Integer>();
	private String tipoDenuncia;
	private TextView meioTransporte;
	private TextView linha;
	private TextView estacao;
	private CheckBox dentro;
	private TextView sentido;
	private EditText vagao;
	private String denuncia;
	
	static {
		LINHAS.put("1-Azul", R.array.linha1);
		LINHAS.put("2-Verde", R.array.linha2);
		LINHAS.put("3-Vermelha", R.array.linha3);
		LINHAS.put("4-Amarela", R.array.linha4);
		LINHAS.put("5-Lilás", R.array.linha5);
		
		LINHAS.put("7-Rubi", R.array.linha7);
		LINHAS.put("8-Diamante", R.array.linha8);
		LINHAS.put("9-Esmeralda", R.array.linha9);
		LINHAS.put("10-Turquesa", R.array.linha10);
		LINHAS.put("11-Coral", R.array.linha11);
		LINHAS.put("12-Safira", R.array.linha12);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button denunciaComercio = (Button) findViewById(R.id.denunciaComercio);
		denunciaComercio.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				gravaTipoDenuncia("Comércio irregular");
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
		
		final Button denunciaSomAlto = (Button) findViewById(R.id.denunciaSomAlto);
		denunciaSomAlto.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				gravaTipoDenuncia("Som Alto");
			}
		});
		
		final Button denunciaOutros = (Button) findViewById(R.id.denunciaOutros);
		denunciaOutros.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				gravaTipoDenuncia("Denúncia");
			}
		});

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.sobre:
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setMessage(this.getString(R.string.sobre))
	    	       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	        	   dialog.cancel();
	    	           }
	    	       });
	    	AlertDialog alert = builder.create();
	    	alert.show();
	        return true;
	    case R.id.quit:
	    	finish();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	private void gravaTipoDenuncia(String tipo) {
		this.tipoDenuncia = tipo;
		setContentView(R.layout.local);
		// inicializa campos de informacao do local
		meioTransporte = (TextView) findViewById(R.id.meioTransporte2);
		linha = (TextView) findViewById(R.id.linha2);
		estacao = (TextView) findViewById(R.id.estacao2);
		estacao = (TextView) findViewById(R.id.estacao2);
		dentro = (CheckBox) findViewById(R.id.dentro2);
		sentido = (TextView) findViewById(R.id.sentido2);
		vagao = (EditText) findViewById(R.id.vagao2);

		showMeioTransporte();
	}

	// metodo auxiliar para resetar as listas ja escolhidas a partir de certo
	// ponto
	private void limpaLocal(int passo) {
		switch (passo) {
		case 1:
			linha.setText("");
		case 2:
			estacao.setText("");
			sentido.setText("");
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
		final CharSequence[] items = { "CPTM", "Metrô" };
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
		final String meio = meioTransporte.getText().toString();
		final CharSequence[] items = meio.equalsIgnoreCase("CPTM") ? getResources()
				.getStringArray(R.array.linhas_trem) : getResources()
				.getStringArray(R.array.linhas_metro);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Linha do " + meioTransporte.getText());
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				linha.setText(items[item]);
				showDentro();
				showEstacao();
				limpaLocal(2);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private void showDentro() {
		dentro.setVisibility(View.VISIBLE);
		final TextView dentro1 = (TextView) findViewById(R.id.dentro1);
		dentro1.setVisibility(View.VISIBLE);
		dentro.setOnClickListener(new View.OnClickListener() {
			final Button estacao1 = (Button) findViewById(R.id.estacao1);
			public void onClick(View v) {
				if (dentro.isChecked()) {
		             estacao1.setText("Próx. Estação");
		             showSentido();
		             showVagao();
		         } else {
		        	 estacao1.setText("Estação");
		        	 hideSentido();
		        	 hideVagao();
		         }
				estacao1.requestFocus();
			}
		});
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
		final String linha = this.linha.getText().toString();
		final CharSequence[] items = getResources().getStringArray(LINHAS.get(linha));
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Estação");
		final Button continuar = (Button) findViewById(R.id.continuar);
		continuar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				criaDenuncia();
			}
		});
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				estacao.setText(items[item]);
				continuar.setVisibility(View.VISIBLE);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private void showSentido() {
		this.sentido.setVisibility(View.VISIBLE);
		final Button sentido = (Button) findViewById(R.id.sentido1);
		sentido.setVisibility(View.VISIBLE);
		sentido.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				chooseSentido();
			}
		});
	}
	
	private void hideSentido() {
		final Button sentido = (Button) findViewById(R.id.sentido1);
		sentido.setVisibility(View.INVISIBLE);
		this.sentido.setVisibility(View.INVISIBLE);
	}

	private void chooseSentido() {
		final String linha = this.linha.getText().toString();
		final CharSequence[] estacoes = getResources().getStringArray(LINHAS.get(linha));
		final CharSequence[] items = { estacoes[0], estacoes[estacoes.length-1]};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Sentido");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				sentido.setText(items[item]);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private void showVagao() {
		final TextView vagao = (TextView) findViewById(R.id.vagao1);
		vagao.setVisibility(View.VISIBLE);
		this.vagao.setVisibility(View.VISIBLE);
	}
	
	private void hideVagao() {
		final TextView vagao = (TextView) findViewById(R.id.vagao1);
		vagao.setVisibility(View.INVISIBLE);
		this.vagao.setVisibility(View.INVISIBLE);
	}
	
	private void criaDenuncia() {
		denuncia = tipoDenuncia + " na linha " + linha.getText() + ". ";
		if(dentro.isChecked()) {
			denuncia += "Trem sentido " + sentido.getText();
			denuncia += " próx. da estação ";
		} else {
			denuncia += "Estação ";
		}
		denuncia +=  estacao.getText() + ".";
		if(vagao.getText().length()>0) {
			denuncia += " Carro " + vagao.getText() + ".";
		
		}

		setContentView(R.layout.send);
		
		final TextView texto = (TextView) findViewById(R.id.texto);
		texto.setText(denuncia);
		
		final Button enviar = (Button) findViewById(R.id.enviar);
		enviar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				denuncia = texto.getText().toString();
				confirmar();
			}
		});
		
		TextView.OnEditorActionListener editListener = new TextView.OnEditorActionListener(){
			
			public boolean onEditorAction(TextView text, int actionId, KeyEvent event) {
				if(actionId == EditorInfo.IME_ACTION_SEND){
					confirmar();
				}
				return true;
			}

		};
		texto.setOnEditorActionListener(editListener);
	}
	
	private void confirmar() {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(this.getString(R.string.envio))
    	       .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   enviar(false);
    	           }
    	       })
    	       .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   dialog.cancel();
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.show();
	}
	
	private void enviar(boolean teste) {
		//TODO alterar para o SmsManager (trabalho enorme para pegar o resultado)
		Intent sendIntent = new Intent(Intent.ACTION_VIEW);
		sendIntent.putExtra("address", meioTransporte.getText().equals("Trem") ? this.getString(R.string.telefone_trem) : this.getString(R.string.telefone_metro));
        sendIntent.putExtra("sms_body", denuncia); 
        sendIntent.setType("vnd.android-dir/mms-sms");
        startActivity(sendIntent);
		
	}
}