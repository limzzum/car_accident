package com.project.car_accident;

import androidx.fragment.app.FragmentActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import com.google.android.maps.GeoPoint;


import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,View.OnClickListener {

    private GoogleMap mMap;
    private Geocoder geocoder;
    private Button button;
    private EditText editText;
Double lat,lng;

    Context mContext;

    Button btn_maps;
    Button btn_chart;
    Button btn_danger;
    Button btn_how;
    Button btn_info;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    /*    Button maps=(Button)findViewById(R.id.maps);
        maps.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });*/
/*Button weather=(Button)findViewById(R.id.how);
weather.setOnClickListener(new Button.OnClickListener() {
   @Override
    public void onClick(View view) {
        Intent intent = new Intent(MapsActivity.this, weatherActivity.class);
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lng);
        startActivity(intent);
    }
});*/

        mContext = this;
        initData();



    }

    public void initData(){
        btn_maps =findViewById(R.id.maps);
        btn_chart = findViewById(R.id.chart);
        btn_danger = findViewById(R.id.danger);
        btn_how = findViewById(R.id.how);
        btn_info = findViewById(R.id.info);

        btn_maps.setOnClickListener(this);
        btn_chart.setOnClickListener(this);
        btn_danger.setOnClickListener(this);
        btn_how.setOnClickListener(this);
        btn_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == btn_maps){
            Intent intent = new Intent(mContext,MapsActivity.class);
            intent.putExtra("lat", lat);
            intent.putExtra("lng", lng);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }else if(v == btn_chart){
            Intent intent = new Intent(mContext,ChartActivity.class);
            intent.putExtra("lat", lat);
            intent.putExtra("lng", lng);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }else if(v == btn_danger){
            Intent intent = new Intent(mContext,DangerActivity.class);
            intent.putExtra("lat", lat);
            intent.putExtra("lng", lng);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }else if(v == btn_how){
            Intent intent = new Intent(MapsActivity.this, weatherActivity.class);
            intent.putExtra("lat", lat);
            intent.putExtra("lng", lng);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }else if(v == btn_info){
            Intent intent = new Intent(mContext,InfoActivity.class);
            intent.putExtra("lat", lat);
            intent.putExtra("lng", lng);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        geocoder = new Geocoder(this);

        LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
        /*mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger2))).position(new LatLng(37.66,127.04)).title("도봉구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger2))).position(new LatLng(37.57,127.04)).title("동대문"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger3))).position(new LatLng(37.51,126.93)).title("동작"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger2))).position(new LatLng(37.60,126.92)).title("은평구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger3))).position(new LatLng(37.63,127.02)).title("강북구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger1))).position(new LatLng(37.53,127.12)).title("강동구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger1))).position(new LatLng(37.51,127.04)).title("강남구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger2))).position(new LatLng(37.55,126.84)).title("강서구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger3))).position(new LatLng(37.45,126.90)).title("금천구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger2))).position(new LatLng(37.49,126.88)).title("구로구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger2))).position(new LatLng(37.47,126.95)).title("관악구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger2))).position(new LatLng(37.53,127.08)).title("광진구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger3))).position(new LatLng(37.57,126.97)).title("종로구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger3))).position(new LatLng(37.56,126.99)).title("중구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger3))).position(new LatLng(37.60,127.09)).title("중랑구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger3))).position(new LatLng(37.56,12.90)).title("마포구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger1))).position(new LatLng(37.65,127.05)).title("노원구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger3))).position(new LatLng(37.48,127.03)).title("서초구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger3))).position(new LatLng(37.57,126.93)).title("서대문구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger1))).position(new LatLng(37.58,127.01)).title("성북구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger3))).position(new LatLng(37.56,127.03)).title("성동구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger2))).position(new LatLng(37.51,127.10)).title("송파구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger1))).position(new LatLng(37.51,126.86)).title("양천구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger3))).position(new LatLng(37.53,126.96)).title("용산구"));
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.danger3))).position(new LatLng(37.52,126.89)).title("영등포구"));*/

        mMap.addMarker(new MarkerOptions().position(DEFAULT_LOCATION).title("Marker in Seoul"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION,12));
        lat=37.56;
        lng=126.97;

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng point) {
                MarkerOptions mOptions = new MarkerOptions();
                // 마커 타이틀
               // mOptions.title("마커 좌표");
                Double latitude = point.latitude; // 위도
                Double longitude = point.longitude; // 경도
                // 마커의 스니펫(간단한 텍스트) 설정
                //mOptions.snippet(latitude.toString() + ", " + longitude.toString());
                // LatLng: 위도 경도 쌍을 나타냄
                //mOptions.position(new LatLng(latitude, longitude));

                mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude)));
                lat=latitude;
                lng=longitude;
               // googleMap.clear();
                // 마커(핀) 추가
               //googleMap.addMarker(mOptions);

            }
        });

        // 버튼 이벤트
        button=findViewById(R.id.button);
        editText=findViewById(R.id.editText);

        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                String str=editText.getText().toString();
                List<Address> addressList = null;
                try {
                    // editText에 입력한 텍스트(주소, 지역, 장소 등)을 지오 코딩을 이용해 변환
                    addressList = geocoder.getFromLocationName(
                            str, // 주소
                            10); // 최대 검색 결과 개수
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println(addressList.get(0).toString());
                // 콤마를 기준으로 split
                String []splitStr = addressList.get(0).toString().split(",");
                String address = splitStr[0].substring(splitStr[0].indexOf("\"") + 1,splitStr[0].length() - 2); // 주소
                System.out.println(address);

                String latitude = splitStr[10].substring(splitStr[10].indexOf("=") + 1); // 위도
                String longitude = splitStr[12].substring(splitStr[12].indexOf("=") + 1); // 경도
                System.out.println(latitude);
                System.out.println(longitude);
                lat=Double.parseDouble(latitude);
                lng=Double.parseDouble(longitude);

                // 좌표(위도, 경도) 생성
                LatLng point = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
//                // 마커 생성
//                MarkerOptions mOptions2 = new MarkerOptions();
//                mOptions2.title("search result");
//                mOptions2.snippet(point.toString());
//                mOptions2.position(point);
//                // 마커 추가
//                mMap.addMarker(mOptions2);
                // 해당 좌표로 화면 줌
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));

            }
        });
        ////////////////////



    }
}

