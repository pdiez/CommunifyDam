package com.communifydam.app.communify;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.*;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.*;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;

import android.support.annotation.ColorInt;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements DialogCloseListener {

    ListView lv;
    String email_usuario;
    Usuario usuario;
    ArrayList<Comunidad> comunidades = new ArrayList<Comunidad>();
    ArrayList<Comunidad> comunidadesFull = new ArrayList<Comunidad>();
    ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();
    FloatingActionButton fab;
    Toolbar myToolbar;
    private LinearLayout coordinator;
    private SwipeRefreshLayout swipeLayout;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    protected SwipeRefreshLayout.OnRefreshListener
            mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener()
    {

        @Override
        public void onRefresh() {
            Log.v("datos_usuarioo", "refrescando");
            refrescaComunidades();
        }
    };

    StorageReference mStorage;
    private ProgressDialog mProgressDialog;
    private static final int GALLERY_INT =1 ;
    Uri fotoURL;
    Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //cargamos la UI
        mStorage = FirebaseStorage.getInstance().getReference();
        mProgressDialog = new ProgressDialog(this);
        setContentView(R.layout.activity_main);
        myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        lv = (ListView) findViewById(R.id.lvAnuncios);
        cargaComunidadesFull();


        coordinator = (LinearLayout) findViewById(R.id.coordinator);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(mOnRefreshListener);

       lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition =
                        (lv == null || lv.getChildCount() == 0) ?
                                0 : lv.getChildAt(0).getTop();
                swipeLayout.setEnabled( topRowVerticalPosition >= 0);
            }
        });


        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder, String tag) {
                Glide.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Glide.clear(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx, String tag) {
                //define different placeholders for different imageView targets
                //default tags are accessible via the DrawerImageLoader.Tags
                //custom ones can be checked via string. see the CustomUrlBasePrimaryDrawerItem LINE 111
                if (DrawerImageLoader.Tags.PROFILE.name().equals(tag)) {
                    return DrawerUIUtils.getPlaceHolder(ctx);
                } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name().equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(com.mikepenz.materialdrawer.R.color.primary).sizeDp(56);
                } else if ("customUrlItem".equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(R.color.md_red_500).sizeDp(56);
                }

                //we use the default one for
                //DrawerImageLoader.Tags.PROFILE_DRAWER_ITEM.name()

                return super.placeholder(ctx, tag);
            }
        });

        //animacion FAB
        /*YoYo.with(Techniques.Wave)
                .duration(400)
                .repeat(1)
                .playOn(findViewById(R.id.fabAddAnuncio));*/

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

    private void cargaComunidadesFull() {
        Query q = database.getReference("comunidades").orderByKey();
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot ) {
                comunidadesFull.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot coms : dataSnapshot.getChildren()) {
                        Comunidad c = coms.getValue(Comunidad.class);
                        comunidadesFull.add(c);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void datosUsuario() {
        Log.v("datos_usuario", "datos usuario");
        //Comprobamos si el usuario esta en FB y tiene el nodo creado, o lo tenemos que crear.
        Query q = database.getReference("usuarios").child(mAuth.getCurrentUser().getUid());
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("datos_usuario", "onDataChange");
                if (dataSnapshot.exists()) {
                    usuario = dataSnapshot.getValue(Usuario.class);
                    Log.v("datos_usuario", usuario.getImagen());
                    //cargamos sus comunidades;
                    pintaBurger();
                    if (usuario.getNombre().isEmpty()) {
                        rellenaPerfil();
                    }
                    Log.v("datos_usuario", usuario.getEmailUsuario());
                    refrescar();
                 //   refrescaComunidades();


                } else {
                    writeNewUser();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void pintaBurger() {

        //Cabecera Hamburger
       if (usuario.getImagen()==null) {
            usuario.setImagen("@drawable/ic_people");
        }
        Log.v("datos_usuario", usuario.getImagen());
        final AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withSelectionListEnabledForSingleProfile(false)
                .withHeaderBackground(R.drawable.gradheader)
                .addProfiles(
                       new ProfileDrawerItem().withName(usuario.getNombre()).withEmail(usuario.getEmailUsuario())
                                .withIcon(usuario.getImagen())
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        Toast.makeText(getApplicationContext(),"Hola",Toast.LENGTH_LONG);
                        return false;
                    }
                })
                .build();



        Drawer result = new DrawerBuilder()
                .withAccountHeader(headerResult)
                .withActivity(this)
                .withActionBarDrawerToggle(true)
                .withTranslucentStatusBar(true)
                .withToolbar(myToolbar)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Crear Comunidad").withIcon(R.drawable.ic_home_trans).withIdentifier(1),
                        new PrimaryDrawerItem().withName("Unirse a comunidad").withIcon(R.drawable.ic_home_trans).withIdentifier(2),
                        new PrimaryDrawerItem().withName("Salir de una comunidad").withIcon(R.drawable.ic_home_trans).withIdentifier(3),
                        new PrimaryDrawerItem().withName("Cambiar foto de perfil").withIcon(R.drawable.ic_people).withIdentifier(4),
                        new PrimaryDrawerItem().withName("Crear un anuncio").withIcon(R.drawable.ic_home_trans).withIdentifier(5),
                        new PrimaryDrawerItem().withName("Cerrar sesión").withIcon(R.drawable.ic_logout).withIdentifier(6),
                        new PrimaryDrawerItem().withName("Salir").withIcon(R.drawable.common_google_signin_btn_icon_disabled).withIdentifier(7)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if(position==1) {
                            addComunidad();
                        } else if (position==2) {
                            joinComunidad();
                        } else if (position==3) {
                           //leaveComunidad();
                        } else if (position==4) {
                            // cambiarFoto();

                            Intent intent = new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(intent, GALLERY_INT);
                            pintaBurger();
                        } else if (position==5) {
                            addAnuncio();
                        } else if (position==6) {
                            mAuth.signOut();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        } else if (position==7) {
                            finish();
                        }
                        return false;
                    }
                })
                .build();

    }

    private void joinComunidad() {

      /*  final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle(R.string.mainDialogEntrarComunidad);
        dialog.setContentView(R.layout.join_comunidad);
        dialog.show();
        final Spinner spn = (Spinner) dialog.findViewById(R.id.spnJoinCom);

        List<String> spinnerArray =  new ArrayList<String>();
        for(Comunidad c : comunidadesFull) {
            spinnerArray.add(c.getNombre());
        }
        if (!spinnerArray.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);

            spn.setAdapter(adapter);
            spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    EditText txt = (EditText) dialog.findViewById(R.id.edtPin) ;
                    if (!comunidadesFull.get(position).getPin().isEmpty()) {
                        txt.setText(comunidadesFull.get(position).getPin());
                        txt.setTag(comunidadesFull.get(position));
                    }
                    txt.setVisibility(View.VISIBLE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            Button btn = (Button) dialog.findViewById(R.id.btnJoinCom);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText txt = (EditText) dialog.findViewById(R.id.edtPin) ;
                    Comunidad c = (Comunidad) txt.getTag();
                    if (!comunidades.contains(c)) {
                        usuario.getComunidades().add(c.getUid());
                        updateUserData();
                    }
                }
            });

        }*/
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setTitle(R.string.mainDialogEntrarComunidad);
        dialog.setContentView(R.layout.join_comunidad);
        dialog.show();
        final Spinner spn = (Spinner) dialog.findViewById(R.id.spnJoinCom);

        List<String> spinnerArray =  new ArrayList<String>();
        for(Comunidad c : comunidadesFull) {
            spinnerArray.add(c.getNombre());
        }
        if (!spinnerArray.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);

            spn.setAdapter(adapter);
            final int[] posicion_para_validar = {0};
            spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    EditText txt = (EditText) dialog.findViewById(R.id.edtPin) ;
                    posicion_para_validar[0] = position;
                    if (!comunidadesFull.get(position).getPin().isEmpty()) {
                        //txt.setText(comunidadesFull.get(position).getPin());
                        txt.setHint("PIN");
                        txt.setTag(comunidadesFull.get(position));
                    }
                    txt.setVisibility(View.VISIBLE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            Button btn = (Button) dialog.findViewById(R.id.btnJoinCom);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText txt = (EditText) dialog.findViewById(R.id.edtPin) ;
                    String pin_usuario = String.valueOf(txt.getText());
                    if (pin_usuario.equals(String.valueOf(comunidadesFull.get(posicion_para_validar[0]).getPin()))){
                        Comunidad c = (Comunidad) txt.getTag();
                        if (!comunidades.contains(c)) {
                            if(usuario.getComunidades()==null) {
                                ArrayList<String> coms = new ArrayList<String>();
                                usuario.setComunidades(coms);
                            }
                            usuario.getComunidades().add(c.getUid());
                            updateUserData();
                            dialog.dismiss();
                            tostar(getString(R.string.joined_community) + " " + c.getNombre());
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "PIN not valid.",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }


    }


    //escribe en FB el nodo del usuario autentificado
    private void writeNewUser() {

        String email = mAuth.getCurrentUser().getEmail().toString();
        Usuario u = new Usuario(email);
        String uid = mAuth.getCurrentUser().getUid();
        u.setImagen("@drawable/ic_people");
        ArrayList<String> c = new ArrayList<String>();
        u.setComunidades(c);

        DatabaseReference dbusuario = database.getReference("usuarios").child(uid);
        dbusuario.push();
        dbusuario.setValue(u);
        if (u.getNombre().isEmpty()) {
            rellenaPerfil();
        }

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
        Log.v("refrescaComunidades", "refresca");
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
                                    Log.v("datos_usuario", "Llamando a refrescaAnuncios");
                                    refrescaAnuncios();
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

    private void refrescaAnuncios() {
        Log.v("datos_usuario", "refrescaAnuncios");
        DatabaseReference dbanuncios = database.getReference("anuncios");
        dbanuncios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                anuncios.clear();
                if (dataSnapshot!=null) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        if  (usuario.getComunidades().contains(d.child("communityId").getValue())) {

                            Anuncio a = d.getValue(Anuncio.class);
                            anuncios.add(a);

                        }
                    }
                }
                refrescaLista();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                refrescaLista();
            }
        });


    }

    private void refrescaLista() {
        ViewGroup vg = findViewById(R.id.maincontenedor);
        LayoutInflater inflater = LayoutInflater.from(this);
        View layout = inflater.inflate(R.layout.no_comms,null);
        if (comunidades.isEmpty()) {
           lv.setVisibility(View.INVISIBLE);
           vg.addView(layout);
           fab.setEnabled(false);

        } else {
            vg.removeView(layout);
            lv.setVisibility(View.VISIBLE);
            fab.setEnabled(true);
            AdaptadorAnuncio adapter = new AdaptadorAnuncio(getApplicationContext(), R.layout.mini_anuncio, anuncios);
            lv.setAdapter(adapter);

        }
        Log.v("datos_usuario", "refrescaLista");
        swipeLayout.setRefreshing(false);
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
        Bundle bundle=new Bundle();
        ArrayList<String> lista_comunidades=new ArrayList<String>();
        ArrayList<String> keys_comunidades=new ArrayList<String>();
        for (Comunidad c : comunidades) {
            lista_comunidades.add(c.getNombre());
            keys_comunidades.add(c.getUid());
        }
        bundle.putStringArrayList("comunidadesnombre", lista_comunidades);
        bundle.putStringArrayList("comunidadeskeys", keys_comunidades);
        dg.setArguments(bundle);
        dg.show(getSupportFragmentManager(), null);

    }

    public void addComunidad() {
        ComunidadDialog dg = new ComunidadDialog();
        dg.show(getSupportFragmentManager(), null);
        cargaComunidadesFull();
    }

    @Override
    public void handleDialogClose(DialogInterface dialog) {
        refrescaComunidades();

    }



   private void refrescar() {
        Log.v("datos_usuario", "refrescando antes de runnable");
        swipeLayout.post(new Runnable() {
            @Override
            public void run() {
                Log.v("datos_usuario", "Seteando refresh a true");
                swipeLayout.setRefreshing(true);
                refrescaComunidades();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_INT && resultCode == RESULT_OK){
            mProgressDialog.setTitle("Subiendo....");
            mProgressDialog.setMessage("subiendo foto a firebase");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            Uri uri = data.getData();
            StorageReference filePath = mStorage.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    mProgressDialog.dismiss();

                    Uri descargarFoto = taskSnapshot.getDownloadUrl();
                    usuario.setImagen(descargarFoto.toString());
                    updateUserData();
                    fotoURL = descargarFoto;
                    drawable=loadImageFromWeb(usuario.getImagen());


                    Toast.makeText(MainActivity.this, "La imagen fue subida correctamente",Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
    private Drawable loadImageFromWeb(String url)
    {
        try
        {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        }catch (Exception e) {
            System.out.println("Exc="+e);
            return null;
        }
    }
}
