package ru.mbsl.fcdirektiva.test;

import java.util.List;

import ru.mbsl.fcdirektiva.database.DataBaseManager;
import ru.mbsl.itemTable.OrderDetailItem;
import ru.mbsl.models.FactoryModel;
import ru.mbsl.models.IModel;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.MediumTest;

public class TestModelOrderDetail extends AndroidTestCase {

	IModel model;
	OrderDetailItem item;
	@Override
	protected void setUp() throws Exception {
		// Инициализируем менаджер с контекстом тестов
		DataBaseManager.getInstance(getContext());		
		model = FactoryModel.getOrderDetailModel();	
		item = new OrderDetailItem();
		super.setUp();
	}
	
	/**
	 * Сохраннение элемента в таблицу
	 */
	@MediumTest
	public void testSaveInsert(){				
		long index = model.save(item);		
		assertTrue(index > 0);
	}

	/**
	 * Очистка всей таблицы
	 */
	@MediumTest
	public void testClearTable(){		
		model.save(item);		
		model.clearDataBase();
		List<?> list = model.getItemsAll();
		assertTrue(list.size() == 0);
	}

	/**
	 * Удаление элемента
	 */
	@MediumTest
	public void testDeleteItem(){
		item._id = model.save(item);
		int index = model.removeItem(item._id);
		assertEquals(1, index);
	}
	
	/**
	 * Получение списка элементов
	 */
	@MediumTest
	public void testGetItemsAll(){
		model.clearDataBase();
		model.save(item);
		model.save(item);
		model.save(item);
		model.save(item);
		List<?> list = model.getItemsAll();
		assertTrue(list.size() == 4);
	}
	
	/**
	 * Получение элемента по идентификатору
	 */
	@MediumTest
	public void testGetItem(){
		OrderDetailItem item = new OrderDetailItem();
		item.cost = 123.123;
		item.face = 1;
		item.id_client = 1;
		item.id_orderheader = 1;
		item.id_product = 1;
		item.photo_path = "path";
		item.product_name = "name";
		item.rest = 1;
		item.shelf = 1;
		item._id = model.save(item);
		OrderDetailItem temp = (OrderDetailItem)model.getItem(item._id);
		assertTrue(item.equals(temp));
	}
	
	/**
	 * Проверка обновления элемента в базе
	 */
	@MediumTest
	public void testUpdateItem(){
		OrderDetailItem item = new OrderDetailItem();
		item.cost = 123.123;
		item.face = 1;
		item.id_client = 1;
		item.id_orderheader = 1;
		item.id_product = 1;
		item.photo_path = "path";
		item.product_name = "name";
		item.rest = 1;
		item.shelf = 1;
		item._id = model.save(item);
		item.cost = 123.1232;
		item.face = 12;
		item.id_client = 12;
		item.id_orderheader = 12;
		item.id_product = 12;
		item.photo_path = "path2";
		item.product_name = "name2";
		item.rest = 12;
		item.shelf = 12;
		model.save(item);
		OrderDetailItem temp = (OrderDetailItem)model.getItem(item._id);
		assertTrue(item.equals(temp));
	}
}
