import com.agungkusuma.core.domain.model.Anime
import com.agungkusuma.core.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteAnimeUseCase(
    private val repository: AnimeRepository
) {
    operator fun invoke(): Flow<List<Anime>> = repository.getFavoriteAnime()
}
