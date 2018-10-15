import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import model.Photo;
import model.PhotoSize;
import util.PhotoDownloader;
import util.PhotoProcessor;
import util.PhotoSerializer;

import java.io.IOException;
import java.util.List;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhotoCrawler {

    private static final Logger log = Logger.getLogger(PhotoCrawler.class.getName());

    private final PhotoDownloader photoDownloader;

    private final PhotoSerializer photoSerializer;

    private final PhotoProcessor photoProcessor;

    private final ObservableTransformer schedulersTransformer = observable -> observable.subscribeOn(Schedulers.io());

    @SuppressWarnings("unchecked")
    public <T> ObservableTransformer<T, T> applySchedulers() {
        return (ObservableTransformer<T, T>) schedulersTransformer;
    }

    public PhotoCrawler() throws IOException {
        this.photoDownloader = new PhotoDownloader();
        this.photoSerializer = new PhotoSerializer("./photos");
        this.photoProcessor = new PhotoProcessor();
    }

    public void resetLibrary() throws IOException {
        photoSerializer.deleteLibraryContents();
    }

    public void downloadPhotoExamples() {
        try {
            Observable<Photo> processedPhotos = processPhotos(photoDownloader.getPhotoExamples());
            processedPhotos
                    .compose(applySchedulers())
                    .subscribe(photoSerializer::savePhoto);

        } catch (IOException e) {
            log.log(Level.SEVERE, "Downloading photo examples error", e);
        }
    }

    public void downloadPhotosForQuery(String query){
        Observable<Photo> processedPhotos = processPhotos(photoDownloader.searchForPhotos(query));
        processedPhotos
                .compose(applySchedulers())
                .subscribe(photoSerializer::savePhoto);
    }

    public void downloadPhotosForMultipleQueries(List<String> queries){
        Observable<Photo> processedPhotos = processPhotos(photoDownloader.searchForPhotos(queries));
        processedPhotos
                .compose(applySchedulers())
                .subscribe(photoSerializer::savePhoto);
    }

    Observable<Photo> processPhotos(Observable<Photo> photos){
        PhotoProcessor processor = new PhotoProcessor();
        Observable<Photo> validPhotos = photos.filter(processor::isPhotoValid);
        Observable<Photo> mediumPhotos = validPhotos
                .filter(photo -> PhotoSize.resolve(photo) == PhotoSize.MEDIUM)
                .buffer(5, TimeUnit.SECONDS)
                .flatMap(Observable::fromIterable);
        Observable<Photo> largePhotos =
                validPhotos
                        .map(processor::convertToMiniature)
                        .observeOn(Schedulers.computation());

        return Observable.merge(mediumPhotos, largePhotos);
    }
}
