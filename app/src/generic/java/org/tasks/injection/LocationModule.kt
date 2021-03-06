package org.tasks.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import org.tasks.location.MapFragment
import org.tasks.location.MapboxMapFragment
import org.tasks.location.MapboxSearchProvider
import org.tasks.location.PlaceSearchProvider

@Module
class LocationModule {
    @Provides
    @ActivityScoped
    fun getPlaceSearchProvider(@ApplicationContext context: Context): PlaceSearchProvider {
        return MapboxSearchProvider(context)
    }

    @Provides
    @ActivityScoped
    fun getMapFragment(@ApplicationContext context: Context): MapFragment {
        return MapboxMapFragment(context)
    }
}