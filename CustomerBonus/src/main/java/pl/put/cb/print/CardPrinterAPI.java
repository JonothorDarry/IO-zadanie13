package pl.put.cb.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

/**
 * Represents naive implementation of the card-printing API.
 */
public class CardPrinterAPI {

	//Zmiana liczby drukarek z 10 do 30 - throughput zwiększa się z 2.0/sec do 5.8/sec
    private static final int NUMBER_OF_PRINTERS = 30;

    private static final CardPrinterAPI instance = new CardPrinterAPI();

    private Logger logger;

    private Semaphore printers_pool;

    private CardPrinterAPI(){
        printers_pool = new Semaphore(NUMBER_OF_PRINTERS);
        logger = LoggerFactory.getLogger(CardPrinterAPI.class);
    }

    public static CardPrinterAPI getInstance(){
        return instance;
    }

    public void printCard(String text){
        try {
            printers_pool.acquire();
            logger.debug("printing... " + text);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.debug(e.getLocalizedMessage());
        } finally {
            printers_pool.release();
        }
    }

    public int getAvailablePrinters(){
        return printers_pool.availablePermits();
    }

}
