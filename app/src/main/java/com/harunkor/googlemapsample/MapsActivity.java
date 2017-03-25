package com.harunkor.googlemapsample;

import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-33.868716, 151.209155);  // konum 1 belirliyoruz.
        LatLng sydney2 = new LatLng(-33.868693,151.208020);  // konum 2 belirleme


        Location l1=new Location("one");  //lokasyon 1 belirleme
        l1.setLatitude(sydney.latitude);
        l1.setLongitude(sydney.longitude);

        Location l2=new Location("two");    //lokasyon 2 belirleme
        l2.setLongitude(sydney2.longitude);
        l2.setLatitude(sydney2.latitude);

       final float  distance = l2.distanceTo(l1);  // iki lokasyon arasında uzaklık


        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.getUiSettings().setZoomControlsEnabled(true);  // harita üzerinde zoom butonları aktif etme
        mMap.getUiSettings().setMapToolbarEnabled(false);   // google haritaya yönlenmeyi kapatma

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom   // animasyon ile yakınlaşma
        (new LatLng(sydney.latitude,sydney.longitude),12f));

        // icon ekleme
        MarkerOptions markerOptions= new MarkerOptions().position(sydney);   // marker icon ekleme
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
        markerOptions.title("Evimiz");
        mMap.addMarker(markerOptions);



        MarkerOptions markerOptions2= new MarkerOptions().position(sydney2);  // marker icon 2 ekleme
        markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
        markerOptions2.title("Evimiz");
        mMap.addMarker(markerOptions2);



// çizgi çizme olayı iki marker arası
        PolylineOptions pop= new PolylineOptions();
        pop.add(sydney,sydney2).geodesic(true).width(5).color(Color.RED);
        mMap.addPolyline(pop);


// marker üzerine tıklandığında toast mesaj göster
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTitle().equals("Evimiz"))  // hangi marker burda
                {
                    Toast.makeText(MapsActivity.this, "Evimizde Kahve İçilir "+distance, Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });


//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(sydney)      // Sets the center of the map to Mountain View
//                .zoom(17)                   // Sets the zoom
//                .bearing(90)                // Sets the orientation of the camera to east
//                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
//                .build();                   // Creates a CameraPosition from the builder
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
