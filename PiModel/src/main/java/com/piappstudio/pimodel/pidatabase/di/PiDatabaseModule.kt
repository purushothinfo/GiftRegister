/*
 *
 *  * Copyright 2021 All rights are reserved by Pi App Studio
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *  *
 *
 */

package com.piappstudio.pimodel.pidatabase.di

import android.content.Context
import androidx.room.Room
import com.piappstudio.pimodel.pidatabase.PiGuestDatabase
import com.piappstudio.pimodel.pidatabase.dao.IGuestDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PiDatabaseModule {

    @Singleton
    @Provides
    fun provideGuestDatabase(@ApplicationContext context: Context):PiGuestDatabase {
        return Room.databaseBuilder(context,
            PiGuestDatabase::class.java,
            "Guest_database").fallbackToDestructiveMigration().build()
    }


    @Provides
    fun providesGuestDao(database: PiGuestDatabase): IGuestDao  {
        return database.guestDao()
    }
}

