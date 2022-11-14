/*
 *
 *   *
 *   *  * Copyright 2022 All rights are reserved by Pi App Studio
 *   *  *
 *   *  * Unless required by applicable law or agreed to in writing, software
 *   *  * distributed under the License is distributed on an "AS IS" BASIS,
 *   *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   *  * See the License for the specific language governing permissions and
 *   *  * limitations under the License.
 *   *  *
 *   *
 *
 */

package com.webcroptech.pihome.updateapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.piappstudio.giftregister.R
import com.piappstudio.pitheme.theme.Dimen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen() {
    Scaffold(topBar = {
        SmallTopAppBar(title = {
            Text(text = stringResource(R.string.update_title))
        })
    }) {
        LazyColumn(
            contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(Dimen.half_space),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item() {
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(
                            start = Dimen.double_space,
                            end = Dimen.double_space
                        ),
                ) {

                    Column(
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.update) ,
                            contentDescription = stringResource(R.string.image),
                            modifier = Modifier
                                .padding(all = Dimen.double_space)
                                .fillMaxWidth()
                        )


                        Text(
                            text = stringResource(R.string.time_to_update),
                            modifier = Modifier.padding(Dimen.double_space),
                            fontWeight = FontWeight.Black,
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Spacer(modifier = Modifier.height(Dimen.double_space))
                        Text(
                            text = stringResource(R.string.we_add_lost_of_new_features_and),
                            modifier = Modifier.padding(
                                start = Dimen.double_space,
                                end = Dimen.double_space
                            ),
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.outline
                        )

                        Text(
                            text = stringResource(R.string.
                            fix_some_bug_to_make_your_exprince_as),
                            modifier = Modifier.padding(
                                start = Dimen.double_space,
                                end = Dimen.double_space
                            ),
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.outline
                        )

                        Text(
                            text = stringResource(R.string.smooth_as_possible),
                            modifier = Modifier.padding(
                                start = Dimen.double_space,
                                end = Dimen.double_space
                            ),
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.outline
                        )

                        Spacer(modifier = Modifier.height(Dimen.double_space))

                        Button(onClick = { }) {
                            Text(
                                text = stringResource(R.string.update_app),
                                modifier = Modifier.padding(
                                    start = Dimen.double_space,
                                    end = Dimen.double_space
                                ),
                                fontWeight = FontWeight.Medium,
                                style = MaterialTheme.typography.titleMedium
                            )

                        }
                        Spacer(modifier = Modifier.height(Dimen.space))
                        Text(
                            text = stringResource(R.string.not_now), Modifier.padding(Dimen.double_space),
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }

            }

        }
    }
}
