package com.communifydam.app.communify;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.materialdrawer.*;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.*;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView lv;
    String email_usuario;
    Usuario usuario;
    ArrayList<Comunidad> comunidades = new ArrayList<Comunidad>();
    FloatingActionButton fab;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private static Integer[] images = {
            android.R.drawable.ic_btn_speak_now,
            android.R.drawable.ic_lock_idle_low_battery,
            android.R.drawable.ic_menu_crop,
            android.R.drawable.ic_menu_call,
            android.R.drawable.ic_menu_day,
            android.R.drawable.ic_media_ff,
            android.R.drawable.ic_media_pause,
            android.R.drawable.ic_menu_sort_by_size
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        //cargamos la UI
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);


        //Cabecera Hamburger
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.mipmap.ic_home_background)
                .addProfiles(
                        new ProfileDrawerItem().withName("Oscar Atos").withEmail("novoyniaunquemematen@gmail.com").withIcon(getResources().getDrawable(R.drawable.ic_launcher_foreground, getTheme()))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();

        //Menu Hamburger
       /* Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withActionBarDrawerToggle(true)
                .withTranslucentStatusBar(true)
                .withToolbar(myToolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("A").withIcon(R.drawable.ic_home_trans).withIdentifier(1),
                        new PrimaryDrawerItem().withName("B").withIcon(R.drawable.ic_edit).withBadge("22").withBadgeStyle(new BadgeStyle(Color.RED, Color.RED)).withIdentifier(2).withSelectable(false),
                        new PrimaryDrawerItem().withName("C").withIcon(R.drawable.ic_home_black_24dp).withIdentifier(3),
                        new PrimaryDrawerItem().withName("D").withIcon(R.drawable.ic_logout).withIdentifier(4),
                        new PrimaryDrawerItem().withDescription("A more complex sample").withName("E").withIcon(R.drawable.material_drawer_circle_mask).withIdentifier(5),
                        new SectionDrawerItem().withName("HHH"),
                        new SecondaryDrawerItem().withName("F").withIcon(R.drawable.material_drawer_shadow_left),
                        new SecondaryDrawerItem().withName("G").withIcon(R.drawable.common_google_signin_btn_icon_light).withTag("Bullhorn"),
                        new DividerDrawerItem(),
                        new SwitchDrawerItem().withName("Switch").withIcon(R.drawable.material_drawer_badge).withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener),
                        new ToggleDrawerItem().withName("Toggle").withIcon(R.drawable.material_drawer_badge).withChecked(true).withOnCheckedChangeListener(onCheckedChangeListener))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {
                            Toast.makeText(MainActivity.this, ((Nameable) drawerItem).getName().getText(MainActivity.this), Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                })
                .withGenerateMiniDrawer(true)
                .build();

            */
        Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withActionBarDrawerToggle(true)
                .withTranslucentStatusBar(true)
                .withToolbar(myToolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Crear Comunidad de prueba").withIcon(R.drawable.ic_home_trans).withIdentifier(1))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(position==1) {
                            addComunidadTest();
                        }
                        return false;
                    }
                })
                .build();

        //animacion FAB
        YoYo.with(Techniques.RubberBand)
                .duration(900)
                .repeat(2)
                .playOn(findViewById(R.id.fabAddAnuncio));

        fab = findViewById(R.id.fabAddAnuncio);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAnuncio();
            }
        });

        //el usuario tiene el perfil completo?
        datosUsuario();
    }

    private void datosUsuario() {
        //Comprobamos si el usuario esta en FB y tiene el nodo creado, o lo tenemos que crear.
        Query q = database.getReference("usuarios").child(mAuth.getCurrentUser().getUid());
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    usuario = dataSnapshot.getValue(Usuario.class);
                    //cargamos sus comunidades;
                    refrescaComunidades();

                } else {
                    writeNewUser();
                }
                if (usuario.getNombre().isEmpty()) {
                    rellenaPerfil();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    //escribe en FB el nodo del usuario autentificado
    private void writeNewUser() {
        String email = mAuth.getCurrentUser().getEmail().toString();
        Usuario u = new Usuario(email);
        String uid = mAuth.getCurrentUser().getUid();

        DatabaseReference dbusuario = database.getReference("usuarios").child(uid);
        dbusuario.push();
        dbusuario.setValue(u);

        //usuario = u;
    }

    private void writeNewAnuncio(Anuncio a) {
        DatabaseReference dbanuncio = database.getReference("anuncios");
        String keycom = dbanuncio.push().getKey();
        dbanuncio.child(keycom).setValue(a);

        tostar("anuncio añadido");

        refrescaLista();

    }

    private void updateUserData() {
        DatabaseReference dbusuario = database.getReference("usuarios");
        dbusuario.child(mAuth.getCurrentUser().getUid()).setValue(usuario);
    }

    //pide al usuario mas datos del perfil si no los tiene
    private void rellenaPerfil() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.mainDialogPedirPerfilTitulo);

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                usuario.setNombre(input.getText().toString());
                tostar(getResources().getString(R.string.mainDialogPedirPerfilToast) + " " + usuario.getNombre());
                updateUserData();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                usuario.setNombre("Anonimo");
                tostar(getResources().getString(R.string.mainDialogPedirPerfilToast) + " " + usuario.getNombre());
                updateUserData();
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void refrescaComunidades() {
        //buscamos las comunnidades del usuario : usuarios/uid/comunidades/
        Query q = database.getReference("usuarios").child(mAuth.getCurrentUser().getUid()).child("comunidades").orderByKey();
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot ) {
                if (dataSnapshot.exists()) {
                    for(DataSnapshot coms : dataSnapshot.getChildren()) {
                        Query q = database.getReference("comunidades").child(coms.getValue().toString());
                        q.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    comunidades.add(dataSnapshot.getValue(Comunidad.class));
                                    refrescaLista();

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                } else {
                    refrescaLista();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void refrescaLista() {

        if (comunidades.isEmpty()) {
            ViewGroup vg = findViewById(R.id.maincontenedor);
            LayoutInflater inflater = LayoutInflater.from(this);
            View layout = inflater.inflate(R.layout.no_comms,null);
            vg.addView(layout);
            fab.setEnabled(false);

        } else {

            fab.setEnabled(true);

            DatabaseReference dbcomunidades = database.getReference("comunidades");


            lv = findViewById(R.id.lvAnuncios);
            ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();

            String[] titles = {"Anuncio 1", "Anuncio 2", "Anuncio 3", "Anuncio 4", "Anuncio 5", "Anuncio 6", "Anuncio 7", "Anuncio 8"};
            String[] descriptions = {"Blabla bala blabllbaldfa dijfafdaefsadfadsfakfd adf asjfd adfj akfd jkasdkf akd fkjadkfadf ad  fjdsf",
                    "Blabla bala blabllbaldfa dijfafdaefsadfadsfakfd adf asjfd adfj akfd jkasdkf akd fkjadkfadf ad  fjdsf",
                    "Blabla bala blabllbaldfa dijfafdaefsadfadsfakfd adf asjfd adfj akfd jkasdkf akd fkjadkfadf ad  fjdsf",
                    "Blabla bala blabllbaldfa dijfafdaefsadfadsfakfd adf asjfd adfj akfd jkasdkf akd fkjadkfadf ad  fjdsf",
                    "Blabla bala blabllbaldfa dijfafdaefsadfadsfakfd adf asjfd adfj akfd jkasdkf akd fkjadkfadf ad  fjdsf",
                    "Blabla bala blabllbaldfa dijfafdaefsadfadsfakfd adf asjfd adfj akfd jkasdkf akd fkjadkfadf ad  fjdsf",
                    "Blabla bala blabllbaldfa dijfafdaefsadfadsfakfd adf asjfd adfj akfd jkasdkf akd fkjadkfadf ad  fjdsf",
                    "Blabla bala blabllbaldfa dijfafdaefsadfadsfakfd adf asjfd adfj akfd jkasdkf akd fkjadkfadf ad  fjdsf"};

            //Populate the List
            for (int i = 0; i < titles.length; i++) {
                Anuncio item = new Anuncio();

                item.setImagen(images[i].toString());
                item.setTitulo(titles[i]);
                item.setDescripcion(descriptions[i]);
                anuncios.add(item);
            }

            // Set the adapter on the ListView
            AdaptadorAnuncio adapter = new AdaptadorAnuncio(getApplicationContext(), R.layout.mini_anuncio, anuncios);
            lv.setAdapter(adapter);
        }
    }

    private  void tostar(String texto) {
        Toast t = new Toast(getApplicationContext());

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,
                (ViewGroup) findViewById(R.id.lytLayout));

        TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(texto);

        t.setDuration(Toast.LENGTH_LONG);
        t.setView(layout);
        t.show();
    }

    private void addAnuncioTest() {
        Context context = getApplicationContext();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText input1 = new EditText(this);
        input1.setHint(R.string.mainAddAnuncio1);
        layout.addView(input1);

        final EditText input2 = new EditText(this);
        input1.setHint(R.string.mainAddAnuncio2);
        layout.addView(input2);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.mainAddAnuncio);

        builder.setView(layout);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Anuncio a = new Anuncio();
                a.setImagen(images[0].toString());
                a.setTitulo(input1.getText().toString());
                a.setDescripcion(input2.getText().toString());
                a.setCommunityId(usuario.getComunidades().get(0));
                a.setUserId(mAuth.getCurrentUser().getUid());
                a.setFecha("01/02/2015");

                writeNewAnuncio(a);




            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }

    private void addComunidadTest() {
        Comunidad c = new Comunidad();
        c.setNombre("Comunidad de prueba");
        c.setOwnerId(mAuth.getCurrentUser().getUid());
        c.setPin("123456");

              DatabaseReference dbcomunidades = database.getReference("comunidades");
        String keycom = dbcomunidades.push().getKey();
        dbcomunidades.child(keycom).setValue(c);



        ArrayList<String> listacom = new ArrayList<String>();
        listacom.add(keycom);

        usuario.setComunidades(listacom);

        updateUserData();

    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            if (drawerItem instanceof Nameable) {
                Log.i("material-drawer", "DrawerItem: " + ((Nameable) drawerItem).getName() + " - toggleChecked: " + isChecked);
            } else {
                Log.i("material-drawer", "toggleChecked: " + isChecked);
            }
        }
    };


    public void addAnuncio() {
        AnuncioDialog dg = new AnuncioDialog();
        dg.show(getSupportFragmentManager(), null);

    }

}
