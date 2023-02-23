import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ViewModel:androidx.lifecycle.ViewModel() {

    private lateinit var fruits: MutableLiveData<String>
    val listFruits:LiveData<String>

    var count:Int=0

    init {
        fruits=MutableLiveData("Apple")
        listFruits=fruits

    }


    fun icrease(){
        count++
    }

}