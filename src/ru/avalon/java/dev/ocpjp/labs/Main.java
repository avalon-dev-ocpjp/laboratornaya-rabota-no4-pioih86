package ru.avalon.java.dev.ocpjp.labs;

import ru.avalon.java.dev.ocpjp.labs.models.Commodity;
import ru.avalon.java.dev.ocpjp.labs.models.CommodityImpl;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        final Collection<Commodity> commodities = Commodity.random(100);

        /*
         * TODO(Студент): С использованием Java Stream API выполнить задачи из списка:
         * 1. Выполнить поиск самого дорого товара
         * 2. Найти товары с минимальным остатком
         * 3. Найти товары с остатком меньшим 5 и вывести в консоль их названия
         * 4. Подсчитать общую стоимость товаров с учётом их остатка
         * 5. Найти товар, с самым длинным названием и вывести его на экран
         * 6. Выполнить сортировку коллекции commodities
         * 7. Найти среднюю стоимость товаров
         * 8. Найти моду (медиану) стоимости товаров
         */

        Optional<Commodity> mostExpensive = commodities.stream().max(CommodityImpl::compareByPrice);

        if (mostExpensive.isPresent()) {
            System.out.println("The most expensive: " + mostExpensive.get().toString());
            System.out.println();
        }

        Optional<Commodity> lowestResidueOpt = commodities.stream().min(CommodityImpl::compareByResidue);
        if (lowestResidueOpt.isPresent()) {
            Commodity lowestResidue = lowestResidueOpt.get();
            System.out.println("Commodities with minimal residue: ");
            commodities.stream().filter(n -> n.getResidue() == lowestResidue.getResidue()).
                    collect(Collectors.toList()).forEach(n -> System.out.println(n.toString()));
            System.out.println();
        }

        System.out.println("Commodities with residue less than 5: ");
        commodities.stream().filter(x -> x.getResidue() < 5).collect(Collectors.toList()).
                forEach(n -> System.out.println(n.toString()));
        System.out.println();

        double sumCost = commodities.stream().mapToDouble(n -> n.getResidue() * n.getPrice()).sum();
        System.out.print("All commodities cost summary: " + sumCost);
        System.out.println();

        Optional<Commodity> longetNameOpt = commodities.stream().max(CommodityImpl::compareByNameLength);
        if (longetNameOpt.isPresent()) {
            String longetName = longetNameOpt.get().getName();
            System.out.println("Longest commodity's name: " + longetName);
            System.out.println();
        }

        System.out.println("Commodities sorted: ");
        commodities.stream().sorted(CommodityImpl::compareByName).forEach(n -> System.out.println(n.toString()));
        System.out.println();

        double avgPrice = commodities.stream().collect(Collectors.averagingDouble(Commodity::getPrice));
        System.out.println("Commodities average price: " + avgPrice);
        System.out.println();

        System.out.print("Median commodity price: ");
        double[] prices = commodities.stream().sorted(CommodityImpl::compareByPrice).mapToDouble(Commodity::getPrice).
                toArray();
        int pricesCount = prices.length;
        double medianPrice = 0;
        if (prices.length % 2 == 0) {
            medianPrice = (prices[pricesCount / 2] + prices[pricesCount / 2 - 1]) / 2;
        } else {
            medianPrice = prices[pricesCount / 2];
        }
        System.out.println(medianPrice);
    }
}
