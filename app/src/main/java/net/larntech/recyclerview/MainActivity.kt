package net.larntech.recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ItemsAdapter.ClickListener {

    val imagesName = arrayOf(
        ItemsModal("image1","image1 desc",R.drawable.image1),
        ItemsModal("image2","image2 desc",R.drawable.image2),
        ItemsModal("image3","image3 desc",R.drawable.image3),
        ItemsModal("image4","image4 desc",R.drawable.image4),
        ItemsModal("image5","image5 desc",R.drawable.image5),
        ItemsModal("image6","image6 desc",R.drawable.image6),
        ItemsModal("image7","image7 desc",R.drawable.image7),
        ItemsModal("image8","image8 desc",R.drawable.image8)
    )

    val itemModalList = ArrayList<ItemsModal>()

    var itemsAdapter : ItemsAdapter? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        for(items in imagesName){
            itemModalList.add(items)
        }

        itemsAdapter = ItemsAdapter(this);
        itemsAdapter!!.setData(itemModalList);
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = itemsAdapter
    }

    override fun ClickedItem(itemsModal: ItemsModal) {
       Log.e("TAG", itemsModal.name);

        when(itemsModal.name){
            "image1"->
                startActivity(Intent(this@MainActivity, Item1Activity::class.java))
            "image2"->
                startActivity(Intent(this@MainActivity,  Item2Activity::class.java))

             else -> {
                 Toast.makeText(this,"No Action", Toast.LENGTH_LONG).show()
             }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val menuItem = menu!!.findItem(R.id.searchView)

        val searchView = menuItem.actionView as SearchView

        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(filterString: String?): Boolean {
                itemsAdapter!!.filter.filter(filterString)

                return true
            }

            override fun onQueryTextChange(filterString: String?): Boolean {

                itemsAdapter!!.filter.filter(filterString)
                return true
            }

        })



        return true
    }
}