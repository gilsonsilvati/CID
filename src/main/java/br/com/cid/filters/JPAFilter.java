package br.com.cid.filters;

/*
@WebFilter(servletNames = {"Faces Servlet"})
public class JPAFilter implements Filter {
	
	private EntityManagerFactory factory;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.factory = Persistence.createEntityManagerFactory("cidPU");
		
	}
	
	@Override
	public void destroy() {
		this.factory.close();
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		// CHEGADA
		EntityManager manager = this.factory.createEntityManager();
		request.setAttribute("EntityManager", manager);
		manager.getTransaction().begin();
		// CHEGADA
		
		// FACES SERVLET
		chain.doFilter(request, response);
		// FACES SERVLET
		
		// SAÍDA
		try {
			manager.getTransaction().commit();
			
		} catch (Exception e) {
			if (manager.isOpen()) {
				manager.getTransaction().rollback();
			}
			
		} finally {
			if (manager.isOpen()) {
				manager.close();
			}
			
		}
		// SAÍDA
		
	}

}
*/
