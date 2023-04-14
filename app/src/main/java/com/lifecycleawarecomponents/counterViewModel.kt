import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class counterViewModel(val initialVal:Int):androidx.lifecycle.ViewModel() {

    private lateinit var fruits: MutableLiveData<String>
    val listFruits:LiveData<String>

    var count:Int=initialVal

    var secondCounter=2

    init {
        fruits=MutableLiveData("Apple")
        listFruits=fruits

    }

    fun increaseSecondCounter(){
        secondCounter++
    }

    fun icrease(){
        count++
    }

}