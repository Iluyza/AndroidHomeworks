package ru.itis.persikill.androidhomeworks
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.itis.persikill.androidhomeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initFloatingButton()
    }

    private fun initFloatingButton() {
        binding.fbAddRandomItem.setOnClickListener {
            val dialog = CustomDialogFragment(
                onAddButtonClicked = ::addCustomItem
            )
            dialog.show(supportFragmentManager, "Custom dialog")
        }
    }

    private fun initRecyclerView() {
        adapter = MyAdapter(
            differ = ListAdapterDiffUtil(),
            onItemClicked = ::onItemClicked,
            onDeleteClicked = ::onDeleteClicked
        )
        Repository.generateList(20)
        adapter?.submitList(Repository.dataList)
        binding.recyclerView. adapter = adapter
        SwipeToDelete(adapter).attachToRecyclerView(binding.recyclerView)
    }

    private fun onItemClicked() {

    }

    private fun onDeleteClicked(position: Int) {
        Repository.deleteItem(position)
        adapter?.submitList(Repository.dataList)
    }

    private fun addCustomItem(position: Int, item: MyLogic.Item) {
        Repository.addItem(position, item)
        adapter?.submitList(Repository.dataList)
    }
}