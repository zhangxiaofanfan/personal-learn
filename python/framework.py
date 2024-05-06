#!/usr/bin/env python
# -*- coding: utf-8 -*-
import sys
import time


def tow_sum(a, b):
    """
    计算两个数据的和

    :param a: 第一个加数
    :param b: 第二个加数
    :return: 返回两个加数的和
    """
    time.sleep(a + b)
    return a + b


if __name__ == '__main__':
    num1 = int(sys.argv[1])
    num2 = int(sys.argv[2])
    print("第一个加数: {}".format(num1))
    print("第二个加数: {}".format(num2))
    print(tow_sum(num1, num2))
