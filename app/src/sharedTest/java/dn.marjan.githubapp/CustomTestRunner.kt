package dn.marjan.githubapp


import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner


/**
   A custom [AndroidJUnitRunner] used to replace the application used in tests with a
   [TestBaseApplication].
 */
class CustomTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, ApplicationTest::class.java.name, context)
    }
}