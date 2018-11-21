package morteza.packag.com.arptest;

import android.os.Bundle;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtHi)
    TextView txtHi;
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyClerView;
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        linearLayoutManager = new LinearLayoutManager(this);


        io.reactivex.Observable.just("Hey! How you doing?").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

                txtHi.setText(s);

            }
        });
        adapter = new MyAdapter();
        myRecyClerView.setHasFixedSize(true);
        myRecyClerView.setAdapter(adapter);
        myRecyClerView.setLayoutManager(linearLayoutManager);


//        Observable.just("PS4", "XBOX One S", "PS4 Pro", "Xbox One X", "iOS", "Android", "iPhone").subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//
//                adapter.addEntry();
//
//            }
//        });

        Observable.just(new Entry("iPhone X", BigDecimal.valueOf(1500), new Date()),
                new Entry("Nexus 5", BigDecimal.valueOf(1000), new Date()),
                new Entry("Galaxy Note 8", BigDecimal.valueOf(200), new Date())

                ).subscribe(new Consumer<Entry>() {
            @Override
            public void accept(Entry entry) throws Exception {
                adapter.addEntry(entry);
            }
        });



    }
}
