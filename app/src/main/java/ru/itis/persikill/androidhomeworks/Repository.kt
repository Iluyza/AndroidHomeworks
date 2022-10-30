package ru.itis.persikill.androidhomeworks

object Repository {

    var dataList: List<MyLogic> = listOf()
        private set

    private val randomGeneralItems: List<MyLogic> = listOf(
        MyLogic.Item(
            "Disorders of the nervous system may involve the following:\n" +
                    "\n" +
                    "Vascular disorders, such as stroke, transient ischemic attack (TIA), subarachnoid hemorrhage, subdural hemorrhage and hematoma, and extradural hemorrhage",
            "Disorders of the nervous system"
        ),
        MyLogic.Item(
            "The following are the most common general signs and symptoms of a nervous system disorder. However, each individual may experience symptoms differently. Symptoms may include:\n" +
                    "\n" +
                    "Persistent or sudden onset of a headache",
            "Signs and symptoms of nervous system disorders "
        )
    )
    private val randomAdvertisementItems: List<MyLogic> = listOf(
        MyLogic.Advertisement(
            "Burger",
            "https://study.com/cimages/multimages/16/burgerad15179945781952220614.png"
        ),
        MyLogic.Advertisement(
            "Coca cola",
            "https://datantify.com/knowledge/wp-content/uploads/2019/07/coca-cola_the_pause_that_refreshes_1931-610x697.jpg"
        ),
        MyLogic.Advertisement(
            "Snickers",
            "https://purpleaddictcom.files.wordpress.com/2016/10/e067b-snickers2.jpg?w=640"
        )
    )

    fun generateList(size: Int) {
        val list = mutableListOf<MyLogic>()
        var k = 0
        for (i in 0 until size) {
            if (i % 6 == 0) {
                k = 0
                val item =
                    randomAdvertisementItems[(randomAdvertisementItems.indices).random()] as MyLogic.Advertisement
                val newItem = item.copy()
                list.add(newItem)
            } else {
                val item =
                    (randomGeneralItems[(randomGeneralItems.indices).random()] as MyLogic.Item)
                val newItem = item.copy()
                newItem.title = k.toString() + " " + newItem.title
                list.add(newItem)
            }
            k++
        }
        dataList = list

    }

    fun addItem(position: Int, item: MyLogic.Item) {
        val list = dataList.toMutableList()
        if (position >= dataList.size -1 ) {
            list.add(item)
        } else {
            list.add(position, item)
            for (i in position .. dataList.size) {
                if (list[i] is MyLogic.Advertisement && i%6 !=0) {
                    list[i] = list[i-1].also {
                        list[i-1] = list[i]
                    }
                }
            }
        }
        dataList = list.toList()
    }

    fun deleteItem(position: Int) {
        val list = dataList.toMutableList()
        list.removeAt(position)
        for (i in position until dataList.size - 2) {
            if (list[i] is MyLogic.Advertisement && i % 6 != 0) {
                list[i] = list[i + 1].also {
                    list[i + 1] = list[i]
                }
            }
            if (list[list.size - 1] is MyLogic.Advertisement) {
                list.removeAt(list.size - 1)
            }
        }
        dataList = list.toList()
    }
}