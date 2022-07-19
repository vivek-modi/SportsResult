package com.vivek.sportsresult.di

import com.vivek.sportsresult.data.repository.ResultRepository
import com.vivek.sportsresult.di.RepositoryScope.networkSessionScope
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent

object RepositoryScope {
    internal var networkSessionScope: Scope = createNetworkSessionScope()

    private fun createNetworkSessionScope(): Scope {
        return KoinJavaComponent.getKoin()
            .getOrCreateScope("networkSessionID", named(NETWORK_SESSION_SCOPE_NAME))
    }
}

val repositoryModule = module {
    factory { networkSessionScope.get<ResultRepository>() }
}
