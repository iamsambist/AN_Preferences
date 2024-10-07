Let’s Talk about Preferences DataStore ( SharedPreferences Deprecated )
It uses key value pairs, without schema to store the small dataset without defining schema upfront.
Preferences provide fully asynchronous API for Retrieving and Saving Data Using the Power of Kotlin Coroutine and Flow 
It does not provide Full Type Safety

When to use Preferences? What is its Limitation?
Preferences does not support Partial Updates
In Database we can update  a single cell of row but in preferences if we need to update the email part of User Object (proto DataStore )here we read the whole Object, update the email and rewrite the Whole object again.
DataStore is best suited for storing data like:
User preferences (e.g., theme, language, notifications)
Small key-value pairs or structured data
Preferences Does Not Support Storing Custom class Object for this we should use Proto Datastore.
Preferences DataStore is designed for simple key-value pairs where the values can only be of the following types
Int
String
Boolean
Float
Double
Long
Set<String>
Even though Preferences does not Support partial update at a Object level, we can still update the value associated with a key in a preferences without updating other key values.

Key things i Understand in Preferences 
fun getThemeState(): Flow<Boolean>
		I. This Function return a Kotlin StateFlow Variable which can be Directly Collected in a UI Composable something like this.
val isDarkTheme by userPref.getThemeState().collectAsState(initial = false)

In function that return Flow<T> do not need to mark as a suspend function. Because they are designed to be asynchronous data that can be collected from Coroutine Context. Without Suspending the coroutine themselves.
Flow is a Asynchronous Stream that emits the values over the time and collects the value non-blocking way?
When you collect the Flow it doesn’t block the thread. Instead it emit the value at any time.
We can collect the Flow inside the Coroutine Like (in Launched Effect and Coroutine Scope.launch )
val coroutineScope = rememberCoroutineScope()
 LaunchedEffect(Unit) {
 coroutineScope.launch {
 userChoices.getThemeState().collect { isDarkMode -> // Use the collected value } } }



// Collect the Flow<Boolean> from getThemeChoice as a state
val isDarkTheme by userPref.getThemeChoice().collectAsState(initial = false)


