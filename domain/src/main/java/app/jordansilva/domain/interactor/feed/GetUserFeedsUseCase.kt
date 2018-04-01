package app.jordansilva.domain.interactor.feed

import app.jordansilva.domain.interactor.BaseUseCase
import app.jordansilva.domain.interactor.CancelableUseCase
import app.jordansilva.domain.model.Feed
import app.jordansilva.domain.repository.FeedRepository
import kotlinx.coroutines.experimental.Deferred

class GetUserFeedsUseCase(private var feedRepository: FeedRepository) : BaseUseCase(), CancelableUseCase<List<Feed>> {

    override suspend fun execute(): Deferred<List<Feed>> {
        try {
            return async { feedRepository.getUserFeeds() }
        } catch (exception: Exception) {
            throw exception
        }
    }

}