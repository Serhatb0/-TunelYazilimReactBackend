package tunel.tunel.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tunel.tunel.business.abstarcts.ProductService;
import tunel.tunel.core.utilities.results.DataResult;
import tunel.tunel.core.utilities.results.Result;
import tunel.tunel.core.utilities.results.SuccessDataResult;
import tunel.tunel.core.utilities.results.SuccessResult;
import tunel.tunel.dataAccess.abstracts.ProductDao;
import tunel.tunel.entities.concretes.Product;

@Service
public class ProductManager implements ProductService{

	private ProductDao productDao;
	
	
	@Autowired
	public ProductManager(ProductDao productDao) {
		super();
		this.productDao = productDao;
	}

	@Override
	public DataResult<List<Product>> getAll() {
		return new SuccessDataResult<List<Product>>(this.productDao.findAll());
	}

	@Override
	public DataResult<Product> getAllById(int id) {
		return new SuccessDataResult<Product>(this.productDao.findAllById(id));
	}

	@Override
	public Result Add(Product product) {
		this.productDao.save(product);
		return new SuccessResult("Data Kaydedildi");
	}

	@Override
	public Result Delete(int id) {
		this.productDao.deleteById(id);
		return new SuccessResult("Data Silindi");
	}

	@Override
	public Result update(int id, Product product) {
		Product product2 = this.productDao.findAllById(id);
		product2.setProductDescription(product.getProductDescription());
		product2.setProductName(product.getProductName());
		
		this.productDao.save(product2);
		return new SuccessResult("Güncellendi");
	}

	
}
