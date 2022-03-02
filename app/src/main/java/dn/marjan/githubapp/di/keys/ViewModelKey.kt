package dn.marjan.githubapp.di.keys

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass


/*
    ViewModelKey annotation, when used on methods annotated with @Provides (“provider methods”),
    basically says that the services returned by these methods should be inserted into Map.
    The keys in this Map will be of type Class<? extends ViewModel> and the values will be of type <? extends ViewModel> (subclass of ViewModel).
    As a result, Dagger will create an implicit Map filled with Provider<ViewModel> objects and put it onto the objects graph
*/

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)