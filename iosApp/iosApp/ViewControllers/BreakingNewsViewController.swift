import Foundation
import UIKit
import shared
import Longinus



class BreakingNewsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    @IBOutlet weak var preLoader: UIActivityIndicatorView!
    @IBOutlet weak var tableViewBreakingNews: UITableView!
    
    private var newsData: [Article] = []
    var country: String = "us"

    override func viewDidLoad() {
        super.viewDidLoad()
        //
        self.tableViewBreakingNews.register(UINib(nibName: "NewsViewCell", bundle: nil), forCellReuseIdentifier: "NewsViewCell")
        self.tableViewBreakingNews.delegate = self
        self.tableViewBreakingNews.dataSource = self
        self.tableViewBreakingNews.tableFooterView = UIView()
        fetchNewsArticles()
    }
    private func fetchNewsArticles() {
        let newsapi = NewsApi()
        newsapi.fetchNews(country: country){ (news, error) in
            if let newsData = news?.articles {
                self.newsData = newsData
                self.tableViewBreakingNews.reloadData()
                self.preLoader.isHidden = true
            } else {
                print(error?.localizedDescription ?? "error")
                self.preLoader.isHidden = true
            }
        }
    }
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 220.0
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return newsData.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "NewsViewCell", for: indexPath) as? NewsViewCell else {
            fatalError()
        }
        let url = URL(string: newsData[indexPath.row].urlToImage!)
        cell.labelDate.text = newsData[indexPath.row].publishedAt
        cell.labelArticle.text = newsData[indexPath.row].title
        cell.labelCategory.text = newsData[indexPath.row].source?.name
        cell.articleImage.lg.setImage(with:url, options: [.progressiveBlur, .imageWithFadeAnimation])
        
        return cell
    }

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
   



        
    }

    override func viewDidAppear(_ animated: Bool) {
       
    }
}
