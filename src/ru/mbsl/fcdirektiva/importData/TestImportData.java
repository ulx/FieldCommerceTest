package ru.mbsl.fcdirektiva.importData;

import java.util.List;

import org.json.JSONException;

import ru.mbsl.fcdirektiva.AcyncTasks.TaskDataImport;
import ru.mbsl.fcdirektiva.database.DataBaseManager;
import ru.mbsl.fcdirektiva.itemTable.ClientItem;
import ru.mbsl.fcdirektiva.models.ClientProductsModel;
import ru.mbsl.fcdirektiva.models.ClientsModel;
import ru.mbsl.fcdirektiva.models.FactoryModel;
import ru.mbsl.fcdirektiva.models.IModel;
import ru.mbsl.fcdirektiva.models.OrderDetailModel;
import ru.mbsl.fcdirektiva.models.OrderHeaderModel;
import ru.mbsl.fcdirektiva.models.ProductsModel;
import android.app.Activity;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.MediumTest;

public class TestImportData extends AndroidTestCase {
	IModel model;

	@Override
	protected void setUp() throws Exception {
		DataBaseManager.getInstance(getContext());
		ClientsModel clientModel = FactoryModel.getClientsModel();
		ProductsModel productModel = FactoryModel.getProductsModel();
		ClientProductsModel clientProductsModel = FactoryModel
				.getClientProductsModel();
		OrderDetailModel orderDetailModel = FactoryModel.getOrderDetailModel();
		OrderHeaderModel orderHeaderModel = FactoryModel.getOrderHeaderModel();
		clientModel.clearDataBase();
		productModel.clearDataBase();
		clientProductsModel.clearDataBase();
		orderDetailModel.clearDataBase();
		orderHeaderModel.clearDataBase();
		super.setUp();
	}

	/**
	 * Сохраннение элемента в таблицу парсинг json клиенты
	 */
	@MediumTest
	public void testImportClient() {
		TaskDataImport importClient = new TaskDataImport();
		model = FactoryModel.getClientsModel();
		model.clearDataBase();
		String json = "{\"clients\":[{\"id\":1,\"product_ids\":[28,29,57,58,59,173,174,176,182,464,467,472,473,765,773,779,1629,1630,1631,1632,1633,1634,1640,1641,1642,1648,1656,1661,1666,1671,1676,1681,1686,1691,1696,1701],\"address\":\"Бирюлевская д.21 к.1\",\"name\":\"7 континент\"},{\"id\":2,\"product_ids\":[28,29,57,58,59,173,174,176,182,464,467,472,473,765,773,779,1629,1630,1631,1632,1633,1634,1640,1641,1642,1648,1656,1661,1666,1671,1676,1681,1686,1691,1696,1701],\"address\":\"Ак. Варги  д.36\",\"name\":\"7 континент\"},{\"id\":3,\"product_ids\":[28,29,57,58,59,173,174,176,182,464,467,472,473,765,773,779,1629,1630,1631,1632,1633,1634,1640,1641,1642,1648,1656,1661,1666,1671,1676,1681,1686,1691,1696,1701],\"address\":\"Красного Маяка д.2 'Б'\",\"name\":\"7 континент\"},{\"id\":4,\"product_ids\":[28,29,57,58,59,173,174,176,182,464,467,472,473,765,773,779,1629,1630,1631,1632,1633,1634,1640,1641,1642,1648,1656,1661,1666,1671,1676,1681,1686,1691,1696,1701],\"address\":\"Медынская д.7\",\"name\":\"7 континент\"},{\"id\":5,\"product_ids\":[28,29,57,58,59,173,174,176,182,464,467,472,473,765,773,779,1629,1630,1631,1632,1633,1634,1640,1641,1642,1648,1656,1661,1666,1671,1676,1681,1686,1691,1696,1701],\"address\":\"Подольских Курсантов  д.10\",\"name\":\"7 континент\"},{\"id\":6,\"product_ids\":[28,29,57,58,59,173,174,176,182,464,467,472,473,765,773,779,1629,1630,1631,1632,1633,1634,1640,1641,1642,1648,1656,1661,1666,1671,1676,1681,1686,1691,1696,1701],\"address\":\"Рокотова д.5\",\"name\":\"7 континент\"},{\"id\":7,\"product_ids\":[28,29,57,58,59,173,174,176,182,464,467,472,473,765,773,779,1629,1630,1631,1632,1633,1634,1640,1641,1642,1648,1656,1661,1666,1671,1676,1681,1686,1691,1696,1701],\"address\":\"Днепропетровская д 4'А' стр.1\",\"name\":\"7 континент\"},{\"id\":8,\"product_ids\":[28,29,57,58,59,173,174,176,182,464,467,472,473,765,773,779,1629,1630,1631,1632,1633,1634,1640,1641,1642,1648,1656,1661,1666,1671,1676,1681,1686,1691,1696,1701],\"address\":\"Россошанская д.7 к.1\",\"name\":\"7 континент\"},{\"id\":9,\"product_ids\":[28,29,57,58,59,173,174,176,182,464,467,472,473,765,773,779,1629,1630,1631,1632,1633,1634,1640,1641,1642,1648,1656,1661,1666,1671,1676,1681,1686,1691,1696,1701],\"address\":\"Харьковская д. 2\",\"name\":\"7 континент\"},{\"id\":10,\"product_ids\":[28,29,57,58,59,173,174,176,182,464,467,472,473,765,773,779,1629,1630,1631,1632,1633,1634,1640,1641,1642,1648,1656,1661,1666,1671,1676,1681,1686,1691,1696,1701],\"address\":\"Чертановская  д.45\",\"name\":\"7 континент\"}]}";
		try {
			importClient.saveClients(json);
		} catch (JSONException e) {
			assertTrue(0 > 1);
			e.printStackTrace();
		}
		List<?> list = model.getItemsAll();
		assertTrue(list.size() > 0);
	}

	/**
	 * Сохраннение элемента в таблицу парсинг json продукты
	 */
	@MediumTest
	public void testImportProduct() {
		TaskDataImport importClient = new TaskDataImport();
		model = FactoryModel.getProductsModel();
		model.clearDataBase();
		String json = "{\"products\":[{\"id\":28,\"manufactor\":\"АЙЕР\",\"name\":\"Водка 'Айер' 0.5, 0.500\"},"
				+ "{\"id\":29,\"manufactor\":\"АЙЕР\",\"name\":\"Водка 'Айер' 42% 0.5, 0.500\"},"
				+ "{\"id\":57,\"manufactor\":\"ВИНИМПОРТ\",\"name\":\"Коньяк Армянский 'Арвинко Таперакан' выдер. 7 лет (Армения) 0.5, 0.500\"},"
				+ "{\"id\":58,\"manufactor\":\"ВИНИМПОРТ\",\"name\":\"Коньяк Армянский 'Арвинко' пятилетний (Армения) 0.5, 0.500\"},"
				+ "{\"id\":59,\"manufactor\":\"ВИНИМПОРТ\",\"name\":\"Коньяк Армянский 'Арвинко' трехлетний (Армения) 0.5, 0.500\"},"
				+ "{\"id\":173,\"manufactor\":\"ДЕРБЕНТ\",\"name\":\"Коньяк Российский категории КВ 'Дербент' 40% (Дербент) 0.5, 0.500\"},"
				+ "{\"id\":174,\"manufactor\":\"ДЕРБЕНТ\",\"name\":\"Коньяк Российский категории КВВК 'Каспий' 40% (Дербент) 0.5, 0.500\"},"
				+ "{\"id\":176,\"manufactor\":\"ДЕРБЕНТ\",\"name\":\"Коньяк Российский пятилетний 'Пять звёздочек' 40% (Дербент) 0.5, 0.500\"},"
				+ "{\"id\":182,\"manufactor\":\"ДЕРБЕНТ\",\"name\":\"Коньяк Российский четырёхлетний 'Старая Крепость' 40% (Дербент) 0.5, 0.500\"},"
				+ "{\"id\":464,\"manufactor\":\"КВЗ\",\"name\":\"Вино 'Крымское' стол. бел. п/сл. (Крымский винный завод) 0.7, 0.700\"},"
				+ "{\"id\":467,\"manufactor\":\"КВЗ\",\"name\":\"Вино 'Крымское' стол. красн. п/сл. (Крымский винный завод) 0.7, 0.700\"},"
				+ "{\"id\":472,\"manufactor\":\"КВЗ\",\"name\":\"Вино игр. 'Крымское' красн. п/сл. (Крымский винный завод) 0.75, 0.750\"},"
				+ "{\"id\":473,\"manufactor\":\"КВЗ\",\"name\":\"Шампанское Российское 'Крымское' п/сл. (Крымский винный завод) 0.75, 0.750\"},"
				+ "{\"id\":765,\"manufactor\":\"ПРАСКОВЕЙСКОЕ\",\"name\":\"Напиток крепкий виноградный 'Косогоров самогон №5' 0.7, 0.700\"},"
				+ "{\"id\":773,\"manufactor\":\"РВКК\",\"name\":\"Вино ст.'Каберне Зол.Амфора' кр.сух (Украина) 0.75, 0.750\"},"
				+ "{\"id\":779,\"manufactor\":\"РВКК\",\"name\":\"Вино ст.'Шардоне Зол.Амфора' бел.сух. (Украина) 0.7, 0.700\"},"
				+ "{\"id\":1629,\"manufactor\":\"Заказ\",\"name\":\"Заказ, .\"},"
				+ "{\"id\":1630,\"manufactor\":\"ЮТА\",\"name\":\"Вино Либенурфюрдих бел п/сл, 1\"},"
				+ "{\"id\":1631,\"manufactor\":\"ЮТА\",\"name\":\"Вино Золотая Балка Крим классик игрист бел п/слад., 0,75\"},"
				+ "{\"id\":1632,\"manufactor\":\"ЮТА\",\"name\":\"Вино Золотая Балка Крим классик игрист бел п/сух., 0,75\"},"
				+ "{\"id\":1633,\"manufactor\":\"Весталко\",\"name\":\"Водка Брызги Балтики Премиум Люкс , 0,5\"},"
				+ "{\"id\":1634,\"manufactor\":\"Весталко\",\"name\":\"Коньяк Королевский Замок 4- лет, 0,5\"},"
				+ "{\"id\":1640,\"manufactor\":\"ЮТА\",\"name\":\"Напиток винный газированный «Боска Аниверсари» бел. п/сл , 0,75\"},"
				+ "{\"id\":1641,\"manufactor\":\"ЮТА\",\"name\":\"Напиток винный газированный «Боска Аниверсари» бел. слад , 0,75\"},"
				+ "{\"id\":1642,\"manufactor\":\"ЮТА\",\"name\":\"Напиток винный газированный «Боска Аниверсари» бел. пл/сух 0,75, 0,75\"},"
				+ "{\"id\":1648,\"manufactor\":\"Весталко\",\"name\":\"Брызги Балтики вино игристое бел. пол/сл., 0,75\"},"
				+ "{\"id\":1656,\"manufactor\":\"РУСВИНТОРГ\",\"name\":\"Алиготе Крымское бел. сух.(Инкерман), 0,75\"},"
				+ "{\"id\":1661,\"manufactor\":\"РУСВИНТОРГ\",\"name\":\"Бастардо Старый Крым кр. сух. (Инкерман), 0,75\"},"
				+ "{\"id\":1666,\"manufactor\":\"РУСВИНТОРГ\",\"name\":\"Древний Херсонес кр. п/сл по (Инкерман) , 0,75\"}]}";
		try {
			importClient.saveProduct(json);
		} catch (JSONException e) {
			assertTrue(0 > 1);
			e.printStackTrace();
		}
		List<?> list = model.getItemsAll();
		assertTrue(list.size() > 0);
	}
}
