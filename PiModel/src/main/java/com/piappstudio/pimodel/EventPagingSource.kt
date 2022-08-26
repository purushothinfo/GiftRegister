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

package com.piappstudio.pimodel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.piappstudio.pimodel.database.PiDataRepository
import javax.inject.Inject

class EventPagingSource @Inject constructor (private val repository:PiDataRepository ): PagingSource<Int, EventInfo>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 0
    }

    override fun getRefreshKey(state: PagingState<Int,EventInfo>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EventInfo> {
        val position = params.key?: INITIAL_PAGE_INDEX
        val allEventsList = repository.fetchEvents()
        return LoadResult.Page(
                data = allEventsList,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position-1,
                nextKey = if (allEventsList.isEmpty()) null else position+1
        )

    }

}