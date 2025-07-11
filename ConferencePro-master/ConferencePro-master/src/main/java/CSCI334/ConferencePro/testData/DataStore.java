package CSCI334.ConferencePro.testData;

import org.springframework.stereotype.Component;

@Component
public class DataStore {
    public String[] firstNames = {
            "John", "Emma", "Liam", "Olivia", "Noah", "Ava", "William", "Sophia", "James", "Isabella",
            "Logan", "Mia", "Benjamin", "Charlotte", "Mason", "Amelia", "Elijah", "Harper", "Oliver", "Evelyn",
            "Jacob", "Abigail", "Lucas", "Emily", "Michael", "Elizabeth", "Alexander", "Mila", "Henry", "Ella",
            "Daniel", "Avery", "Jackson", "Scarlett", "Sebastian", "Grace", "Aiden", "Chloe", "Matthew", "Victoria",
            "Samuel", "Riley", "David", "Luna", "Joseph", "Zoe", "Carter", "Penelope", "Owen", "Layla"
    };

    public String[] lastNames = {
            "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
            "Anderson", "Thomas", "Jackson", "White", "Harris", "Clark", "Lewis", "Young", "Lee", "Walker",
            "Hall", "Allen", "King", "Wright", "Scott", "Green", "Baker", "Adams", "Nelson", "Hill",
            "Ramirez", "Campbell", "Mitchell", "Roberts", "Carter", "Phillips", "Evans", "Turner", "Torres", "Parker",
            "Collins", "Edwards", "Stewart", "Flores", "Morris", "Nguyen", "Murphy", "Rivera", "Cook", "Rogers"
    };

    public String[] organizationNames = {
            "ByteTech",
            "TechConnect",
            "InnovaTech",
            "CodeCraft",
            "CyberLogic",
            "DataFusion",
            "TechGenius",
            "ByteWave",
            "Innovatech",
            "DigitalNexus",
            "CodeTech",
            "eSolutions",
            "TechGurus",
            "Innovision",
            "DataTech",
            "TechLab",
            "TechMasters",
            "LogicTech",
            "TechSavvy",
            "DigitalTech"
    };

    public String[] subjects = {
        "Computer Science",
        "Software Engineering",
        "Database Systems",
        "Computer & IT",
        "Virtual Reality",
        "Artificial Intelligence",
        "Data Mining",
        "Software Design",
        "Algorithms & Data Structures",
        "Information Systems"
    };

    public String[] paperTitles = {
            "Enhancing Security in Internet of Things (IoT) Networks through Blockchain Technology",
            "Machine Learning-Based Intrusion Detection System for Network Security",
            "Advancements in Natural Language Processing for Sentiment Analysis in Social Media",
            "Exploring the Potential of Augmented Reality in Educational Environments",
            "Privacy-Preserving Techniques for Secure Data Sharing in Cloud Computing",
            "Efficient Resource Allocation in Edge Computing Environments",
            "Secure and Scalable Blockchain Consensus Protocols for Distributed Systems",
            "Advancements in Deep Learning for Computer Vision Tasks",
            "Enhancing Data Privacy in Internet of Things (IoT) Environments through Federated Learning",
            "Exploring Explainable Artificial Intelligence for Transparent Decision-Making"
    };

    public String[] paperAbstracts = {
            "This research paper explores the use of blockchain technology to enhance security in Internet of Things (IoT) networks. IoT networks are susceptible to various security threats due to the large number of connected devices and the inherent vulnerabilities in their communication protocols. The paper proposes leveraging the decentralized and tamper-resistant nature of blockchain to establish trust, ensure data integrity, and enhance privacy in IoT networks. The study presents a framework that integrates blockchain with IoT, highlighting its potential to address security concerns and enable secure and reliable communication among IoT devices.",
            "This research paper focuses on the development of an intrusion detection system (IDS) using machine learning techniques to enhance network security. Traditional IDS systems often struggle to effectively detect and mitigate modern-day sophisticated cyber threats. The paper proposes a novel approach that leverages machine learning algorithms to analyze network traffic patterns and identify anomalies indicative of potential intrusions. The study presents experimental results demonstrating the effectiveness of the proposed IDS in accurately detecting various types of network attacks while minimizing false positives. The research contributes to the advancement of network security by harnessing the power of machine learning for proactive threat detection and response.",
            "This research paper explores advancements in natural language processing (NLP) techniques for sentiment analysis in social media data. Sentiment analysis plays a crucial role in understanding public opinion and sentiment trends on social media platforms. The paper presents an in-depth analysis of various NLP methods, including lexicon-based approaches, machine learning algorithms, and deep learning models, applied to sentiment analysis tasks. It discusses the challenges associated with social media data, such as noise and context, and highlights the potential of NLP techniques to improve accuracy and efficiency in sentiment analysis. The findings contribute to the field of NLP by providing insights into effective approaches for sentiment analysis in the context of social media data.",
            "This research paper investigates the potential of augmented reality (AR) technology in educational environments. Traditional teaching methods often struggle to engage students and provide immersive learning experiences. The paper explores how AR can enhance education by overlaying digital information and interactive elements onto the real world. It discusses the benefits of AR in fostering student engagement, improving knowledge retention, and promoting active learning. The research presents case studies and evaluations of AR applications in various educational domains, highlighting the effectiveness of AR in enhancing learning outcomes. The findings contribute to the understanding of AR's transformative role in education and provide insights into its successful implementation.",
            "This research paper focuses on privacy-preserving techniques for secure data sharing in cloud computing environments. Cloud computing offers scalable and convenient data storage and processing, but concerns about data privacy and security remain prominent. The paper investigates cryptographic protocols and privacy-preserving mechanisms that enable users to securely share sensitive data in the cloud while maintaining confidentiality and integrity. It presents a comprehensive analysis of privacy-enhancing technologies, such as homomorphic encryption, secure multiparty computation, and differential privacy. The research contributes to the development of robust privacy-preserving solutions that address the challenges of data sharing in cloud computing, ensuring data security and preserving individual privacy.",
            "This research paper focuses on efficient resource allocation strategies in edge computing environments. Edge computing has emerged as a promising paradigm for processing data closer to the source, reducing latency and enhancing real-time applications. The paper investigates novel algorithms and optimization techniques to allocate computing resources effectively in edge nodes based on workload characteristics and resource availability. It explores dynamic resource management approaches, including task scheduling, load balancing, and energy optimization, to maximize the utilization of edge resources while meeting application requirements. The research findings contribute to improving the efficiency and performance of edge computing systems, enabling the seamless execution of resource-intensive applications at the network edge.",
            "This research paper explores the design and analysis of secure and scalable consensus protocols for distributed systems based on blockchain technology. Consensus protocols are essential for achieving agreement and consistency among nodes in a decentralized network. The paper investigates innovative approaches to enhance the security and scalability of consensus algorithms, addressing challenges such as network latency, resource consumption, and malicious attacks. It presents a comparative evaluation of various consensus protocols, including Proof of Work (PoW), Proof of Stake (PoS), and Practical Byzantine Fault Tolerance (PBFT). The research contributes to the advancement of distributed systems by providing insights into efficient consensus protocols that ensure the integrity and efficiency of blockchain-based networks.",
            "This research paper explores advancements in deep learning techniques for computer vision tasks. Computer vision plays a crucial role in various applications, such as object recognition, image segmentation, and scene understanding. The paper investigates state-of-the-art deep learning models, including convolutional neural networks (CNNs), recurrent neural networks (RNNs), and transformer networks, applied to computer vision tasks. It discusses novel architectures, training strategies, and data augmentation techniques that have improved the accuracy and efficiency of deep learning models. The research provides valuable insights into the advancements in deep learning for computer vision, contributing to the development of more robust and accurate vision-based systems.",
            "This research paper explores the use of federated learning to enhance data privacy in Internet of Things (IoT) environments. IoT devices generate massive amounts of sensitive data, raising concerns about privacy and security. The paper investigates the application of federated learning, a distributed machine learning approach, to train models on decentralized IoT devices without transferring raw data to a central server. It discusses the privacy-preserving aspects of federated learning and presents techniques to mitigate privacy risks. The research findings contribute to the development of privacy-enhancing solutions in IoT, enabling secure and collaborative learning while preserving data privacy.",
            "This research paper focuses on exploring explainable artificial intelligence (XAI) techniques to enable transparent decision-making in complex machine learning models. As AI systems become increasingly prevalent in critical domains, understanding and justifying their decisions are crucial for building trust and ensuring accountability. The paper investigates various XAI approaches, including rule-based explanations, feature importance analysis, and model-agnostic methods. It discusses the challenges and trade-offs involved in balancing explainability and model performance. The research contributes to the development of interpretable AI systems, allowing stakeholders to comprehend and validate the decision-making process behind AI algorithms."
    };

    public String[] textReviews = {
        "The strength of this research paper lies in its comprehensive experimental evaluation, providing solid evidence to support the proposed hypothesis and ensuring the reliability of the results.",
        "One weakness of this research paper is the limited sample size used for data collection, which may raise questions about the generalizability of the findings and the statistical significance of the observed trends.",
        "The research paper demonstrates a strong theoretical foundation by drawing upon a wide range of established theories and models in the field, establishing a solid basis for the proposed framework.",
        "A potential weakness of this research paper is the lack of consideration for alternative approaches or competing theories, limiting the overall depth of the analysis and potentially overlooking important perspectives.",
        "The research paper's strength lies in its innovative methodology, introducing a novel approach that addresses an existing gap in the literature and opens up new avenues for further exploration and experimentation.",
        "This research paper's strength lies in its meticulous data collection process, which involved multiple sources and rigorous quality control measures, ensuring the accuracy and reliability of the presented findings.",
        "One potential weakness of this research paper is the lack of a clear theoretical framework or conceptual model to guide the analysis, leaving room for ambiguity in interpreting the results and establishing causal relationships.",
        "The research paper demonstrates a strong interdisciplinary approach by incorporating perspectives from various fields, enriching the discussion and providing a comprehensive understanding of the topic.",
        "A weakness of this research paper is the limited scope of the study, focusing primarily on a specific geographic region, which may limit the generalizability of the conclusions to a broader context.",
        "The strength of this research paper lies in its insightful discussion of practical implications, offering concrete recommendations and actionable insights that can be applied by practitioners and policymakers in real-world settings.",
        "One notable weakness of this research paper is the absence of a control group, which hinders the ability to establish a causal relationship between the variables under investigation and limits the validity of the conclusions drawn.",
        "The research paper lacks proper statistical analysis, making it challenging to determine the significance of the reported findings and raising concerns about the reliability of the results.",
        "This theoretical research paper explores a novel concept but fails to provide practical implications or actionable recommendations, limiting its immediate relevance to real-world scenarios.",
        "The research paper delves into an abstract theoretical framework, offering intriguing ideas and thought-provoking perspectives, but its practical applications and feasibility remain unclear.",
        "Theoretical in nature, this research paper contributes to the existing body of knowledge by presenting a conceptual framework; however, its impact on practical domains and potential for implementation require further investigation."
    };

    public int[] scoreReviews = {
        2, -1, 3, -2, 1, 3, -3, 2, -1, 1, -2, -3, 0, 0, 0
    };
}
