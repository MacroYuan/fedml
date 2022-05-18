import fedml
import torch
from fedml.cross_silo import Client
from fedml.data.MNIST.data_loader import download_mnist, load_partition_data_mnist

class MyDataset(dataset):


def get_dataloader(dataset, data_dir, batch_size, dataidxs=None):
    dl_obj = MyDataset

    transform_train, transform_test =

    return train_dl, test_dl

def load_data(args):
    download_mnist(args.data_cache_dir)
    fedml.logging.info("load_data. dataset_name = %s" % args.dataset)

    train_ds = MyDataset(args.data_cache_dir, train=True, download=False, transform=)
    """
    Please read through the data loader at to see how to customize the dataset for FedML framework.
    """
    (
        client_num, #客户端数量
        train_data_num,
        test_data_num,
        train_data_global,
        test_data_global,
        train_data_local_num_dict,
        train_data_local_dict,
        test_data_local_dict,
        class_num,
    ) = load_partition_data_mnist(
        args.batch_size,
        train_path=args.data_cache_dir + "MNIST/train",
        test_path=args.data_cache_dir + "MNIST/test",
    )
    """
    For shallow NN or linear models, 
    we uniformly sample a fraction of clients each round (as the original FedAvg paper)
    """
    args.client_num_in_total = client_num
    dataset = [
        train_data_num,
        test_data_num,
        train_data_global,
        test_data_global,
        train_data_local_num_dict,
        train_data_local_dict,
        test_data_local_dict,
        class_num,
    ]
    return dataset, class_num


class LogisticRegression(torch.nn.Module):
    def __init__(self, input_dim, output_dim):
        super(LogisticRegression, self).__init__()
        self.linear = torch.nn.Linear(input_dim, output_dim)

    def forward(self, x):
        outputs = torch.sigmoid(self.linear(x))
        return outputs


if __name__ == "__main__":
    # init FedML framework
    args = fedml.init()

    # init device
    device = fedml.device.get_device(args)

    # load data
    dataset, output_dim = load_data(args)

    # load model (the size of MNIST image is 28 x 28)
    model = LogisticRegression(28 * 28, output_dim)

    # start training
    client = Client(args, device, dataset, model)
    client.run()
