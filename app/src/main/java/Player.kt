import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.util.*
@Parcelize
data class Player(var id : UUID, var name : String, var challenge : String, var text : String, var score : Int, var textInLoop : Boolean, var playerInLoop : Boolean) : Parcelable