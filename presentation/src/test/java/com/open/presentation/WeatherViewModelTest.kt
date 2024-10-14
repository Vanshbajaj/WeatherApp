import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

//    private val getWeatherUseCase: GetWeatherUseCase = mockk()
//    private lateinit var weatherViewModel: com.open.weather.ui.theme.WeatherViewModel

//    @Before
//    fun setUp() {
//        Dispatchers.setMain(Dispatchers.Unconfined)
//        weatherViewModel = com.open.weather.ui.theme.WeatherViewModel(getWeatherUseCase)
//    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks() // Clear mocks after each test
    }

//    @Test
//    fun `Given a valid city, When getWeather is called, Then the state should be updated to Success`() = runTest {
//        // Given
//        val city = "London"
//        val mockResponse = WeatherResponse() // Use appropriate mock data
//        coEvery { getWeatherUseCase(city) } returns flow {
//            emit(WeatherResponse()) // Emit loading state first
//            emit(WeatherResponse(mockResponse.base)) // Emit success state
//        }
//
//        // When
//        weatherViewModel.getWeather(city)
//
//        // Then
//        val states = mutableListOf<WeatherState>()
//        val job = launch {
//            weatherViewModel.weatherState.collect { state ->
//                states.add(state)
//            }
//        }
//
//        advanceUntilIdle() // Wait for all coroutines to finish
//        job.cancel() // Cancel the collection job
//
//        // Assertions
//        assertEquals(2, states.size) // Ensure we have two states collected
//        assertEquals(WeatherState.Loading, states[0]) // Ensure first state is Loading
//        assertEquals(WeatherState.Success(mockResponse), states[1]) // Ensure second state is Success
//    }
//
//    @Test
//    fun `Given a city request, When getWeather is called, Then the state should initially be Loading`() = runTest {
//        // Given
//        val city = "London"
//        coEvery { getWeatherUseCase(city) } returns flow {
//            emit(WeatherResponse()) // Emit loading state
//            // Simulate waiting before success response
//            emit(WeatherResponse()) // You can adjust this as necessary
//        }
//
//        // When
//        weatherViewModel.getWeather(city)
//
//        // Then
//        val states = mutableListOf<WeatherState>()
//        val job = launch {
//            weatherViewModel.weatherState.collect { state ->
//                states.add(state)
//            }
//        }
//
//        advanceUntilIdle() // Allow the coroutine to finish
//        job.cancel() // Cancel the collection job
//
//        // Assertions
//        assertEquals(1, states.size) // Ensure we have only the loading state collected
//        assertEquals(WeatherState.Loading, states[0]) // Ensure initial Loading state
//    }
//
//    @Test
//    fun `Given a city, When getWeather is called, Then the use case should be invoked with the correct parameters`() = runTest {
//        // Given
//        val city = "Paris"
//        val mockResponse = WeatherResponse() // Use appropriate mock data
//        coEvery { getWeatherUseCase(city) } returns flow { emit(mockResponse) }
//
//        // When
//        weatherViewModel.getWeather(city)
//
//        // Allow the coroutine to complete
//        advanceUntilIdle() // Wait for all coroutines to finish
//
//        // Then
////        verify { getWeatherUseCase(city) } // Verify that the use case was called with the correct city
//    }
}
