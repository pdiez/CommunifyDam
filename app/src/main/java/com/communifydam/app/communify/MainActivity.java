package com.communifydam.app.communify;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView lv;

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


        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        //if you want to update the items at a later time it is recommended to keep it in a variable
       // PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("papapap");
        //SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("jejejejeje");

        //header
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

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
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



        refrescaLista();

        YoYo.with(Techniques.Pulse)
                .duration(900)
                .repeat(YoYo.INFINITE)
                .playOn(findViewById(R.id.fabAddAnuncio));

       /* ImageButton btnComunidades = (ImageButton) findViewById(R.id.btnComunidades);
        btnComunidades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pintaComunidades();
            }
        });

        ImageButton btnPerfil = (ImageButton) findViewById(R.id.btnPerfil);
        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
                startActivity(intent);
            }
        }); */


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddAnuncio);

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



    public void pintaComunidades() {

        ArrayList<Comunidad> comunidades = new ArrayList<Comunidad>();
        Comunidad com1 = new Comunidad(true, "Mi barrio", "blabla");
        Comunidad com2 = new Comunidad(false, "13 Rue del Percebe", "blabla");

        comunidades.add(com1);
        comunidades.add(com2);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);


        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.lista_com_dialog, null);
        dialogBuilder.setView(dialogView);

        ListView lvcom = (ListView) dialogView.findViewById(R.id.lvComunidades);
        AdaptadorComunidad adcom = new AdaptadorComunidad(getApplicationContext(), R.layout.mini_comunidad, comunidades);
        lvcom.setAdapter(adcom);

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


    }

    public void refrescaLista() {
        lv = (ListView) findViewById(R.id.lvAnuncios);
        ArrayList<Anuncio> anuncios = new ArrayList<Anuncio>();

        String[] titles = {"Anuncio 1","Anuncio 2","Anuncio 3","Anuncio 4","Anuncio 5","Anuncio 6","Anuncio 7","Anuncio 8"};
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
            Anuncio item = new Anuncio(images[i], titles[i], descriptions[i]);
            anuncios.add(item);
        }

        // Set the adapter on the ListView
        AdaptadorAnuncio adapter = new AdaptadorAnuncio(getApplicationContext(), R.layout.mini_anuncio, anuncios);
        lv.setAdapter(adapter);
    }

}
