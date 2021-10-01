package com.example.shoppinglist.ui.shoppingitems

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.shoppinglist.MainCoroutineRule
import com.example.shoppinglist.getOrAwaitValue
import com.example.shoppinglist.models.domain.ShoppingItem
import com.example.shoppinglist.models.domain.ShoppingList
import com.example.shoppinglist.repository.FakeTestRepository
import com.example.shoppinglist.ui.base.NavigationCommand
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.stopKoin
import java.util.*

@ExperimentalCoroutinesApi
@SmallTest
class ShoppingItemsViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Use a fake repository to be injected into the viewModel
    private lateinit var repository: FakeTestRepository

    // Subject under test
    private lateinit var viewModel: ShoppingItemsViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val item1 = ShoppingItem(1, "Item1", 1, Calendar.getInstance().time, false, 1)
    private val item2 = ShoppingItem(2, "Item2", 2, Calendar.getInstance().time, false, 1)
    private val item3 = ShoppingItem(3, "Item3", 3, Calendar.getInstance().time, true, 1)
    private val item4 = ShoppingItem(4, "Item4", 4, Calendar.getInstance().time, true, 2)
    private val item5 = ShoppingItem(5, "Item5", 5, Calendar.getInstance().time, false, 2)
    private val item6 = ShoppingItem(6, "Item6", 6, Calendar.getInstance().time, false, 3)

    private val list1 = ShoppingList(
        1,
        "List 1",
        Calendar.getInstance().time,
        false,
        mutableListOf(item1, item2, item3)
    )
    private val list2 =
        ShoppingList(2, "List 2", Calendar.getInstance().time, false, mutableListOf(item4, item5))
    private val list3 =
        ShoppingList(3, "List 3", Calendar.getInstance().time, true, mutableListOf(item6))

    private val items = listOf(item1, item2, item3, item4, item5, item6)
    private val lists = listOf(list1, list2, list3)

    @Before
    fun setupViewModel() {
        stopKoin()
        repository = FakeTestRepository(lists.toMutableList(), items.toMutableList())
        viewModel =
            ShoppingItemsViewModel(repository)
    }

    @Test
    fun navToItemEdit_someData_navigationCommandUpdates() {
        // Given some navigation data
        val itemId = 1L
        val listId = 1L
        val destinationLabel = "Test Destination"

        // When calling this function with the navigation data
        viewModel.navToItemEdit(itemId, listId, destinationLabel)

        // Then navigationCommand updates
        val value = viewModel.navigationCommand.getOrAwaitValue()
        assertThat(
            value, `is`(
                NavigationCommand.To(
                    ShoppingItemsFragmentDirections.actionShoppingItemsFragmentToItemEditFragment(
                        itemId,
                        listId,
                        destinationLabel
                    )
                )
            )
        )
    }

    @Test
    fun navToListEdit_someData_navigationCommandUpdates() {
        // Given some navigation data
        val listId = 1L
        viewModel.listId.value = listId
        val destinationLabel = "Test Destination"

        // When calling this function with the navigation data
        viewModel.navToListEdit(destinationLabel)

        // Then navigationCommand updates
        val value = viewModel.navigationCommand.getOrAwaitValue()
        assertThat(
            value, `is`(
                NavigationCommand.To(
                    ShoppingItemsFragmentDirections.actionShoppingItemsFragmentToListEditFragment(
                        listId,
                        destinationLabel
                    )
                )
            )
        )
    }

    @Test
    fun deleteShoppingList_existingListId_showToastIntUpdates() {
        // Given an id of an existing list
        viewModel.listId.value = 1

        // When deleting list
        viewModel.deleteShoppingList()

        // Then showToastInt updates
        val value = viewModel.showToastInt.getOrAwaitValue()
        assertThat(value, `is`(notNullValue()))
    }

    @Test
    fun deleteShoppingList_existingListId_navigationCommandUpdates() {
        // Given an id of an existing list
        viewModel.listId.value = 1

        // When deleting the list
        viewModel.deleteShoppingList()

        // Then navigationCommand updates
        val value = viewModel.navigationCommand.getOrAwaitValue()
        assertThat(
            value,
            `is`(NavigationCommand.To(ShoppingItemsFragmentDirections.actionShoppingItemsFragmentToMainFragment()))
        )
    }

    @Test
    fun deleteShoppingList_notExistingListId_showToastUpdates() {
        // Given not existing list id
        viewModel.listId.value = 0

        // When deleting list
        viewModel.deleteShoppingList()

        // Then showToast updates
        val value = viewModel.showToast.getOrAwaitValue()
        assertThat(value, `is`(notNullValue()))
    }

    @Test
    fun deleteShoppingList_nullListId_showToastIntUpdates() {
        // Given null list id
        viewModel.listId.value = null

        // When deleting list
        viewModel.deleteShoppingList()

        // Then showToastInt updates
        val value = viewModel.showToastInt.getOrAwaitValue()
        assertThat(value, `is`(notNullValue()))
    }

    @Test
    fun setListArchived_trueAndExistingListId_showToastIntUpdates() {
        // Given archiveList true value and existing list id
        val archiveList = true
        viewModel.listId.value = 1

        // When calling the function
        viewModel.setListArchived(archiveList)

        // Then showToastInt updates
        val value = viewModel.showToastInt.getOrAwaitValue()
        assertThat(value, `is`(notNullValue()))
    }

    @Test
    fun setListArchived_falseAndExistingListId_showToastIntUpdates() {
        // Given archiveList false value and existing list id
        val archiveList = false
        viewModel.listId.value = 1

        // When calling the function
        viewModel.setListArchived(archiveList)

        // Then showToastInt updates
        val value = viewModel.showToastInt.getOrAwaitValue()
        assertThat(value, `is`(notNullValue()))
    }

    @Test
    fun setListArchived_existingListId_navigationCommandUpdates() {
        // Given archiveList value and existing list id
        val archiveList = true
        viewModel.listId.value = 1

        // When calling the function
        viewModel.setListArchived(archiveList)

        // Then navigationCommand updates
        val value = viewModel.navigationCommand.getOrAwaitValue()
        assertThat(
            value,
            `is`(NavigationCommand.To(ShoppingItemsFragmentDirections.actionShoppingItemsFragmentToMainFragment()))
        )
    }

    @Test
    fun setListArchived_notExistingListId_showToastIntUpdates() {
        // Given archiveList value and not existing list id
        val archiveList = true
        viewModel.listId.value = 0

        // When calling the function
        viewModel.setListArchived(archiveList)

        // Then showToastInt updates
        val value = viewModel.showToastInt.getOrAwaitValue()
        assertThat(value, `is`(notNullValue()))
    }

    @Test
    fun setListArchived_nullListId_showToastIntUpdates() {
        // Given archiveList value and null list id
        val archiveList = true
        viewModel.listId.value = null

        // When calling the function
        viewModel.setListArchived(archiveList)

        // Then showToastInt updates
        val value = viewModel.showToastInt.getOrAwaitValue()
        assertThat(value, `is`(notNullValue()))
    }

    @Test
    fun reverseItemIsBought_notExistingItemId_showToastIntUpdates(){
        // Given not existing item id
        val itemId = 0L

        // When calling the function
        viewModel.reverseItemIsBought(itemId)

        // Then showToastInt updates
        val value = viewModel.showToastInt.getOrAwaitValue()
        assertThat(value, `is`(notNullValue()))
    }
}