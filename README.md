# IO-zadanie13
Rozwiązanie zadania z IO

W repo w plikach CustomerBonus/1st_task.jmx, CustomerBonus/2nd_task.jmx, CustomerBonus/3rd_task.jmx są kolejne rozwiązania do 
zadań z 13 laboratoriów, których nie oddałem w trakcie zajęć, bo mnie na nich nie było.

1. W pierwszym zadaniu przed zmianą wartość average w summary wynosiła 5810, throughput 1.5/sec; problemem z kodem było
wywoływanie funkcji averageSpendings() w pętli, na dodatek aż dwukrotni; zmiana kodu na:
private List<Customer> s;
private Double v;
....
s=CustomersDAO.getInstance().getCustomers();
v=averageSpendings();
for (Customer customer : s){
    if (v > 0.0 &&
            customer.getSpendings() / v >= threshold){
        customerIds.add(customer.getId());
        logger.debug(customer.getId()+"");
    }
}
return customerIds;
Sprawiła, że czas wykonania spadł z average (time)=5810 i throughput=1.5/sec do average (time)=49 i throughput=20.9/sec

2. W drugim zadaniu throughput wynosi ok. 2.0/sec dla 10 drukarek. Zmiana w pliku CardPrinterAPI.java:
private static final int NUMBER_OF_PRINTERS = 30;
zgodnie z oczekiwaniami zwiększa throughput do ok. 5.8/sec. Co ciekawe, zwiększenie liczby drukarek do 20.000 zwiększa 
throughput jedynie do ok. 6.2/sec

3. Zaimplementaowałem 3. zadanie zgodnie z wymogami(a przynajmniej taką mam nadzieję), najciekawszą kwestią było zmniejszenie 
throughputu printera z 5.8/sec do 1.5/sec - podejrzewam, że jest to spowodowane spowolnieniami w przetwarzaniu wywołanymi 
innymi requestami, których czasy throughputu sumują się do ok. 5 sekund.

W pliku screens dodałem screenshoty z tych 3 testów - pokazuję summary i strukturę projektu.
